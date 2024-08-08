package com.myaxa.core.coin.data

import com.myaxa.core.coin.data.remote.CoinNetworkDataSource
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.network.safeCall
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val networkDataSource: CoinNetworkDataSource,
) {
    suspend fun getCoinsList(currency: Currency = Currency.USD): Result<List<ListCoin>> {

        return networkDataSource.getCoinsList(currency = currency)
            .map { list -> list.map { it.toDomainModel() } }
    }

    suspend fun getCoinDetails(id: CoinId) = safeCall {
        networkDataSource.getCoinDetails(id).map { it.toDomainModel() }
    }
}
