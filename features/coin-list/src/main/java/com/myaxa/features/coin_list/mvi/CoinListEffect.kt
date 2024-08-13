package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.domain.CoinId

sealed interface CoinListEffect {
    data class NavigateToCoinDetails(
        val id: CoinId,
        val name: String,
    ) : CoinListEffect
}
