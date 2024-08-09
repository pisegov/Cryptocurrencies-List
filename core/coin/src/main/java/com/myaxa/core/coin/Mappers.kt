package com.myaxa.core.coin

import com.myaxa.core.coin.domain.CoinDetails
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.core.database.coin.entities.CoinDetailsFull
import com.myaxa.core.database.coin.entities.CoinWithPrices
import java.lang.IllegalArgumentException

internal fun CoinWithPrices.toListCoin(currency: Currency): ListCoin {
    val price = prices.associateBy { it.currency }[currency.name]
        ?: throw IllegalArgumentException("There is no such currency")

    return ListCoin(
        id = coin.coinId,
        name = coin.name,
        symbol = coin.symbol,
        image = coin.image,
        currentPrice = price.currentPrice,
        priceChangePercentage24h = price.priceChangePercentage24h,
    )
}

internal fun CoinDetailsFull.toCoinDetails(): CoinDetails {
    return CoinDetails(
        id = coin.coinId,
        name = coin.name,
        image = coin.image,
        description = description.text,
        categories = categories.map { it.name },
    )
}
