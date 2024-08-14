package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import com.myaxa.core.ui.components.NetworkErrorScreenComponent
import com.myaxa.core.ui.components.ProgressIndicatorComponent
import com.myaxa.features.coin_list.model.LoadingStatus
import com.myaxa.features.coin_list.mvi.CoinListEffect
import com.myaxa.features.coin_list.mvi.Event
import com.myaxa.features.coin_list.mvi.State

@Composable
internal fun CoinListContent(
    uiState: State,
    sendUserEvent: (Event.User) -> Unit,
    handleScreenEffect: (CoinListEffect) -> Unit,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current

    val navigationBarsInsets = WindowInsets.navigationBars.asPaddingValues()
    val startPadding = navigationBarsInsets.calculateStartPadding(layoutDirection)
    val endPadding = navigationBarsInsets.calculateEndPadding(layoutDirection)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = startPadding, end = endPadding)
    ) {
        CoinListToolbarComponent(
            currentCurrency = uiState.currentCurrency,
            onCurrencySelected = { sendUserEvent(Event.User.ChangeCurrency(it)) }
        )
        when {
            uiState.list.isNotEmpty() -> {
                CoinListComponent(
                    uiState = uiState,
                    sendUserEvent = sendUserEvent,
                    handleScreenEffect = handleScreenEffect,
                )
            }

            else -> when (uiState.loadingStatus) {
                LoadingStatus.Loading -> {
                    ProgressIndicatorComponent(modifier = modifier.fillMaxSize())
                }

                is LoadingStatus.Failure -> {
                    NetworkErrorScreenComponent(
                        onRetryClicked = { sendUserEvent(Event.User.LoadInitial) },
                    )
                }

                LoadingStatus.Idle -> {
                    sendUserEvent(Event.User.LoadInitial)
                }
            }
        }
    }
}
