package com.myaxa.features.coin_list.model

import com.myaxa.core.coin.domain.CoinId

internal data class ListCoinUi(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val currentPrice: String,
    val priceChangePercentage: PriceChangePercentage,
)
