package com.myaxa.features.coin_details.model

import androidx.compose.runtime.Stable
import com.myaxa.core.coin.domain.CoinDetails
import com.myaxa.core.coin.domain.CoinId

@Stable
internal data class CoinDetailsUi(
    val id: CoinId,
    val name: String,
    val imageUrl: String,
    val description: String,
    val categories: String,
)

internal fun CoinDetails.toUiModel() = CoinDetailsUi(
    id = id,
    name = name,
    imageUrl = imageUrl,
    description = description,
    categories = categories.joinToString(separator = ", ")
)
