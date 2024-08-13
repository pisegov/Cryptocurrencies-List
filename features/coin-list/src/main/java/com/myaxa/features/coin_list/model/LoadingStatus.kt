package com.myaxa.features.coin_list.model

internal sealed interface LoadingStatus {
    data object Idle : LoadingStatus
    data object Loading : LoadingStatus
    data object Failure : LoadingStatus
}
