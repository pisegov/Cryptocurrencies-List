package com.myaxa.core.viewmodel_inject

import androidx.lifecycle.ViewModelProvider

/**
 * Provides a [UniversalViewModelFactory] instance
 * that creates every ViewModel in the app
 */
interface ViewModelFactoryProvider {
    val viewModelFactory: ViewModelProvider.Factory
}
