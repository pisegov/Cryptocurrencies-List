package com.myaxa.features.coin_details.mvi

/**
 * Side effect for UI that needs to be processed once
 */
sealed interface CoinDetailsEffect {
    data object NavigateBack : CoinDetailsEffect
}
