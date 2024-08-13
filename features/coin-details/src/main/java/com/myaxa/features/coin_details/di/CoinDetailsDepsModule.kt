package com.myaxa.features.coin_details.di

import androidx.lifecycle.ViewModel
import com.myaxa.core.viewmodel_inject.ViewModelKey
import com.myaxa.features.coin_details.CoinDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CoinDetailsDepsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailsViewModel::class)
    internal abstract fun bindCoinDetailsViewModel(impl: CoinDetailsViewModel): ViewModel
}
