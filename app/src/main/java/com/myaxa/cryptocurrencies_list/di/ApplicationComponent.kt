package com.myaxa.cryptocurrencies_list.di

import com.myaxa.core.viewmodel_inject.UniversalViewModelFactoryModule
import com.myaxa.core.viewmodel_inject.ViewModelFactoryProvider
import dagger.Component
import dagger.Module
import javax.inject.Scope


@Component(modules = [ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent : ViewModelFactoryProvider {

    @Component.Factory
    interface Factory {
        fun create(

        ): ApplicationComponent
    }
}

@Module(includes =[UniversalViewModelFactoryModule::class])
interface ApplicationModule

@Scope
annotation class ApplicationScope
