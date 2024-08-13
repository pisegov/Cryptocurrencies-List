package com.myaxa.features.coin_details.mvi

import androidx.compose.runtime.Stable
import com.myaxa.features.coin_details.model.LoadingStatus

@Stable
internal data class State(
    val loadingStatus: LoadingStatus,
) {
    companion object {
        fun initial() = State(loadingStatus = LoadingStatus.Loading)
    }
}
