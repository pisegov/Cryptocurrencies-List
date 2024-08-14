package com.myaxa.features.coin_details.di

import androidx.lifecycle.ViewModel
import com.myaxa.core.viewmodel_inject.ViewModelKey
import com.myaxa.features.coin_details.CoinDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module for exporting feature components
 * so that [Application module][com.myaxa.cryptocurrencies_list.di.ApplicationModule] can resolve
 * dependencies for [CoinDetailsViewModel]
 */
@Module
abstract class CoinDetailsDepsModule {

    /**
     * Binds [CoinDetailsViewModel] to common [ViewModel]
     * for [UniversalViewModelFactory][com.myaxa.core.viewmodel_inject.UniversalViewModelFactory]
     *
     * @see ViewModelKey
     */
    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailsViewModel::class)
    internal abstract fun bindCoinDetailsViewModel(impl: CoinDetailsViewModel): ViewModel
}
