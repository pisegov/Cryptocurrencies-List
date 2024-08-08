package com.myaxa.core.viewmodel_inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Creates every ViewModel in the application
 * @param viewModelProviders map of ViewModel providers for each ViewModel class
 */
@Suppress("UNCHECKED_CAST")
internal class UniversalViewModelFactory @Inject constructor(
    private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return viewModelProviders.getValue(modelClass).get() as T
    }
}

/**
 * Exports [UniversalViewModelFactory] as [ViewModelProvider.Factory]
 * so that every feature can use it without depending on this module
 */
@Module
abstract class UniversalViewModelFactoryModule {

    /**
     * Binds empty map of ViewModel instances to avoid crashes if there is no ViewModel yet
     */
    @Multibinds
    internal abstract fun bindViewModelMap(): Map<Class<out ViewModel>, ViewModel>

    @Binds
    internal abstract fun bindViewModelFactory(impl: UniversalViewModelFactory): ViewModelProvider.Factory
}

/**
 * Designed to annotate [Binds] annotated methods that bind ViewModel implementations
 * into map of class-provider pairs for [UniversalViewModelFactory]
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
