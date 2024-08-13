package com.myaxa.features.coin_list.mvi

import androidx.compose.runtime.Stable
import com.myaxa.core.coin.domain.Currency
import com.myaxa.features.coin_list.model.ListCoinUi

@Stable
internal data class State(
    val currentCurrency: Currency,
    val list: List<ListCoinUi>,
    val loadingStatus: LoadingStatus,
) {
    companion object {
        fun initial() = State(
            currentCurrency = Currency.USD,
            list = emptyList(),
            loadingStatus = LoadingStatus.Loading
        )
    }
}

internal sealed interface LoadingStatus {
    data object Idle : LoadingStatus
    data object Loading : LoadingStatus
    data object Failure : LoadingStatus
}

internal fun State.isRefreshing() = list.isNotEmpty() && loadingStatus is LoadingStatus.Loading

internal fun State.isRefreshFailure() = list.isNotEmpty() && loadingStatus is LoadingStatus.Failure
