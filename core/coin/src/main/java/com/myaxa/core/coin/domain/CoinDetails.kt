package com.myaxa.core.coin.domain


data class CoinDetails(
    val id: CoinId,
    val name: String,
    val imageUrl: String,
    val description: String,
    val categories: List<String>,
)
