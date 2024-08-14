package com.myaxa.core.coin.data.remote

import androidx.annotation.IntRange
import com.myaxa.core.coin.data.remote.model.CoinDetailsRemote
import com.myaxa.core.coin.data.remote.model.ListCoinRemote
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.coin.domain.Currency
import com.myaxa.network.NetworkClientProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun CoinNetworkDataSource(networkClientProvider: NetworkClientProvider): CoinNetworkDataSource {
    return CoinNetworkDataSource(networkClientProvider.client)
}

class CoinNetworkDataSource internal constructor(private val client: HttpClient) {

    /**
     * Loads cryptocurrency list
     *
     * @param currency currency in which to load
     * the equivalent cryptocurrency list
     * @return list of cryptocurrencies in the form of [ListCoinRemote] model
     */

    internal suspend fun getCoinList(
        currency: Currency = Currency.USD,
        @IntRange(from = 1, to = 100) number: Int = 30,
    ): List<ListCoinRemote> = withContext(Dispatchers.IO) {

        client.get(urlString = "coins/markets?vs_currency=${currency.name.lowercase()}&per_page=$number")
            .body<List<ListCoinRemote>>()
    }

    /**
     * Loads cryptocurrency details
     *
     * @param id currency id on a remote server
     * @return remote model of cryptocurrency details
     */
    internal suspend fun getCoinDetails(
        id: CoinId,
    ): CoinDetailsRemote = withContext(Dispatchers.IO) {

        client.get(urlString = "coins/$id?localization=false")
            .body<CoinDetailsRemote>()
    }
}
