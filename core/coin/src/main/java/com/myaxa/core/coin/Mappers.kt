package com.myaxa.core.coin

import com.myaxa.core.coin.domain.CoinDetails
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.core.database.coin.entities.CoinDetailsFull
import com.myaxa.core.database.coin.entities.CoinEntity
import com.myaxa.core.database.coin.entities.CoinPriceEntity
import com.myaxa.core.database.coin.entities.CoinWithPrices

/**
 * Mapper that converts [CoinWithPrices] model from database
 * to [ListCoin] according to the current [Currency]
 * */
internal fun CoinWithPrices.toListCoin(currency: Currency): ListCoin {
    val price = prices.associateBy { it.currency }[currency.name]
        ?: throw IllegalArgumentException("There is no such currency")

    return ListCoin(
        id = coin.coinId,
        name = coin.name,
        symbol = coin.symbol,
        imageUrl = coin.image,
        currentPrice = price.currentPrice,
        priceChangePercentage = price.priceChangePercentage24h,
    )
}

/**
 * Mapper that converts [CoinDetailsFull] model from database to [CoinDetails]
 * */
internal fun CoinDetailsFull.toCoinDetails(): CoinDetails {
    return CoinDetails(
        id = coin.coinId,
        name = coin.name,
        imageUrl = coin.image,
        description = description.text,
        categories = categories.map { it.name },
    )
}

internal fun ListCoin.toCoinEntity() = CoinEntity(
    coinId = id,
    name = name,
    symbol = symbol,
    image = imageUrl
)

internal fun ListCoin.toCoinPrices(currency: Currency) = CoinPriceEntity(
    coinId = id,
    currency = currency.name,
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage,
)
