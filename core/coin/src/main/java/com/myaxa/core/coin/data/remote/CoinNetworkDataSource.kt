package com.myaxa.core.coin.data.remote

import androidx.annotation.IntRange
import com.myaxa.core.coin.data.remote.model.CoinDetailsRemote
import com.myaxa.core.coin.data.remote.model.ListCoinRemote
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.coin.domain.Currency
import com.myaxa.network.NetworkClientProvider
import com.myaxa.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

fun CoinNetworkDataSource(networkClientProvider: NetworkClientProvider): CoinNetworkDataSource {
    return CoinNetworkDataSource(networkClientProvider.client)
}

class CoinNetworkDataSource internal constructor(private val client: HttpClient) {

    internal suspend fun getCoinsList(
        currency: Currency = Currency.USD,
        @IntRange(from = 1, to = 100) number: Int = 30,
    ): Result<List<ListCoinRemote>> = safeCall {

        client.get(urlString = "coins/markets?vs_currency=${currency.name.lowercase()}&per_page=$number")
            .body<List<ListCoinRemote>>()
    }

    internal suspend fun getCoinDetails(id: CoinId): Result<CoinDetailsRemote> = safeCall {

        client.get(urlString = "coins/$id?localization=false")
            .body<CoinDetailsRemote>()
    }
}
