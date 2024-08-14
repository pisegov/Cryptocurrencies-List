package com.myaxa.core.coin.domain

/**
 * Model for displaying coin details
 *
 * @param id remote coin id
 * @param name coin title
 * @param imageUrl: url for the coin logo image
 * @param description coin description
 * @param categories coin category list
 * */
data class CoinDetails(
    val id: CoinId,
    val name: String,
    val imageUrl: String,
    val description: String,
    val categories: List<String>,
)
