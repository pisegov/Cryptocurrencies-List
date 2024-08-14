package com.myaxa.core.coin.data

import com.myaxa.core.coin.data.local.CoinLocalDataSource
import com.myaxa.core.coin.data.remote.CoinNetworkDataSource
import com.myaxa.core.coin.data.remote.model.ListCoinRemote
import com.myaxa.core.coin.domain.CoinDetails
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.core.coin.toCoinDetails
import com.myaxa.core.coin.toListCoin
import com.myaxa.network.safeCall
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val networkDataSource: CoinNetworkDataSource,
    private val localDataSource: CoinLocalDataSource,
) {
    suspend fun getCoinsList(currency: Currency = Currency.USD): Result<List<ListCoin>> = safeCall {

        val remoteList: List<ListCoinRemote> = networkDataSource.getCoinList(currency = currency)

        val list = remoteList.map { it.toDomainModel() }

        localDataSource.insertCoins(list, currency)

        localDataSource.getCoinListWithPrices().map { it.toListCoin(currency) }
    }

    suspend fun getCoinDetails(id: CoinId): Result<CoinDetails> = safeCall {

        val details = networkDataSource.getCoinDetails(id).toDomainModel()

        localDataSource.insertCoinDetails(details)

        localDataSource.getCoinDetails(id).toCoinDetails()
    }
}
