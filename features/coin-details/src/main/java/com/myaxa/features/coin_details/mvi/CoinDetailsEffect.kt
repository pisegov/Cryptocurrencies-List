package com.myaxa.features.coin_details.mvi

sealed interface CoinDetailsEffect {
    data object NavigateBack : CoinDetailsEffect
}
