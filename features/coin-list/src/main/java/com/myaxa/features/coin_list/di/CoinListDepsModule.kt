package com.myaxa.features.coin_list.di

import androidx.lifecycle.ViewModel
import com.myaxa.core.viewmodel_inject.ViewModelKey
import com.myaxa.features.coin_list.CoinListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module for exporting feature components
 * so that [Application module][com.myaxa.cryptocurrencies_list.di.ApplicationModule] can resolve
 * dependencies for [CoinListViewModel]
 */
@Module
abstract class CoinListDepsModule {

    /**
     * Binds [CoinListViewModel] to common [ViewModel]
     * for [UniversalViewModelFactory][com.myaxa.core.viewmodel_inject.UniversalViewModelFactory]
     *
     * @see ViewModelKey
     */
    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    internal abstract fun bindViewModel(impl: CoinListViewModel): ViewModel
}
