package com.myaxa.features.coin_details.mvi

import androidx.compose.runtime.Stable
import com.myaxa.features.coin_details.model.LoadingStatus

/**
 * Screen state model
 * Wraps [loadingStatus] states
 */
@Stable
internal data class State(
    val loadingStatus: LoadingStatus,
) {
    companion object {
        fun initial() = State(loadingStatus = LoadingStatus.Loading)
    }
}

internal fun State.isInitialized() = this.loadingStatus != LoadingStatus.Loading
