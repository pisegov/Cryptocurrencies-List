package com.myaxa.cryptocurrencies_list

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.myaxa.core.viewmodel_inject.ViewModelFactoryProvider
import com.myaxa.cryptocurrencies_list.di.ApplicationComponent
import com.myaxa.cryptocurrencies_list.di.DaggerApplicationComponent

class CryptocurrenciesListApplication : Application(), ViewModelFactoryProvider {
    private val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }

    override val viewModelFactory: ViewModelProvider.Factory
        get() = component.viewModelFactory
}
