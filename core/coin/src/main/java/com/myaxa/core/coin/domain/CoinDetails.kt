package com.myaxa.core.coin.domain


data class CoinDetails(
    val id: CoinId,
    val name: String,
    val image: String,
    val description: String,
    val categories: List<String>,
)
