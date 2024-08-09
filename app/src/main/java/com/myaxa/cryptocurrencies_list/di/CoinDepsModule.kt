package com.myaxa.cryptocurrencies_list.di

import com.myaxa.core.coin.data.local.CoinLocalDataSource
import com.myaxa.core.coin.data.remote.CoinNetworkDataSource
import com.myaxa.core.database.CryptocurrenciesListDatabaseProvider
import com.myaxa.network.NetworkClientProvider
import dagger.Module
import dagger.Provides

@Module
class CoinDepsModule {

    @Provides
    fun provideCoinNetworkDataSource(
        networkClientProvider: NetworkClientProvider,
    ) = CoinNetworkDataSource(networkClientProvider)

    @Provides
    fun provideCoinLocalDataSource(
        databaseProvider: CryptocurrenciesListDatabaseProvider,
    ) = CoinLocalDataSource(databaseProvider)
}
