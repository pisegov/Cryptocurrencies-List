package com.myaxa.cryptocurrencies_list.di

import com.myaxa.core.coin.data.remote.CoinNetworkDataSource
import com.myaxa.network.NetworkClientProvider
import dagger.Module
import dagger.Provides

@Module
class CoinDepsModule {

    @Provides
    fun provideCoinNetworkDataSource(
        networkClientProvider: NetworkClientProvider,
    ): CoinNetworkDataSource {
        return CoinNetworkDataSource(networkClientProvider)
    }
}
