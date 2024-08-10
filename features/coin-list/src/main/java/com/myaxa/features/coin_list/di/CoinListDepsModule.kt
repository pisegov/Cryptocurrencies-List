package com.myaxa.features.coin_list.di

import androidx.lifecycle.ViewModel
import com.myaxa.core.viewmodel_inject.ViewModelKey
import com.myaxa.features.coin_list.CoinListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CoinListDepsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    internal abstract fun bindViewModel(impl: CoinListViewModel): ViewModel
}
