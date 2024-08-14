package com.myaxa.features.coin_list.model

import androidx.compose.runtime.Stable
import com.myaxa.core.coin.domain.CoinId

/**
 * Cryptocurrency in a list ui model
 *
 * @see [com.myaxa.core.coin.domain.ListCoin]
 */
@Stable
internal data class ListCoinUi(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val currentPrice: String,
    val priceChangePercentage: PriceChangePercentage,
)
