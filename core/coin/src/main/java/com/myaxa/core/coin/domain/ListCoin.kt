package com.myaxa.core.coin.domain

data class ListCoin(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage: Double,
)
