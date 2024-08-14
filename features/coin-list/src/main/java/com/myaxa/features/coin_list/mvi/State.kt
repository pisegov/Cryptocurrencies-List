package com.myaxa.features.coin_list.mvi

import androidx.compose.runtime.Stable
import com.myaxa.core.coin.domain.Currency
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.LoadingStatus

/**
 * Screen state model
 *
 * Combines [loadingStatus] states with [list] states (empty or not)
 * and [currentCurrency]
 */
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
            loadingStatus = LoadingStatus.Idle
        )
    }
}

internal fun State.isRefreshing() = list.isNotEmpty() && loadingStatus is LoadingStatus.Loading

internal fun State.isRefreshFailure() = list.isNotEmpty() && loadingStatus is LoadingStatus.Failure
