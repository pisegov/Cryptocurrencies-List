package com.myaxa.core.coin.domain

/**
 * Model for displaying coin list
 *
 * @param id remote coin id
 * @param name coin title
 * @param symbol currency code
 * @param imageUrl: url for the coin logo image
 * @param currentPrice current price in the equivalent of a given currency
 * @param priceChangePercentage current price change percentage in the last 24 hours
 * */
data class ListCoin(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val currentPrice: Double,
    val priceChangePercentage: Double,
)
