package com.myaxa.features.coin_details.model

internal sealed interface LoadingStatus {
    data class Success(val model: CoinDetailsUi) : LoadingStatus
    data object Loading : LoadingStatus
    data object Failure : LoadingStatus
}
