package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.domain.CoinId

/**
 * Side effect for UI that needs to be processed once
 */
sealed interface CoinListEffect {
    data class NavigateToCoinDetails(val id: CoinId, val name: String) : CoinListEffect
}
