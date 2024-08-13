package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.components.PullToRefreshLazyColumnComponent
import com.myaxa.core.ui.components.Snackbar
import com.myaxa.core.ui.theme.DarkGreen
import com.myaxa.core.ui.throttledClickListener.throttledClickable
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.LoadingStatus
import com.myaxa.features.coin_list.model.PriceChangePercentage
import com.myaxa.features.coin_list.mvi.CoinListEffect
import com.myaxa.features.coin_list.mvi.Event
import com.myaxa.features.coin_list.mvi.State
import com.myaxa.features.coin_list.mvi.isRefreshFailure
import com.myaxa.features.coin_list.mvi.isRefreshing

@Composable
internal fun CoinListComponent(
    uiState: State,
    sendUserEvent: (Event.User) -> Unit,
    handleScreenEffect: (CoinListEffect) -> Unit,
    modifier: Modifier = Modifier,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(uiState) {
        if (uiState.isRefreshFailure()) {

            snackbarHostState.showSnackbar(
                message = "Произошла ошибка при загрузке",
                withDismissAction = false,
                actionLabel = "Kek",
                duration = SnackbarDuration.Short
            )

            sendUserEvent(Event.User.SetErrorShown)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        PullToRefreshLazyColumnComponent(
            isRefreshing = uiState.isRefreshing(),
            onRefresh = { sendUserEvent(Event.User.Reload) },
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            uiState.list.forEach {
                item {
                    CoinComponent(
                        coin = it,
                        modifier = Modifier.throttledClickable {
                            handleScreenEffect(
                                CoinListEffect.NavigateToCoinDetails(
                                    id = it.id,
                                    name = it.name,
                                )
                            )
                        })
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { Snackbar(it) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(WindowInsets.navigationBars.asPaddingValues())
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CoinListComponentPreview() {
    val coin = ListCoinUi(
        "bitcoin",
        "Bitcoin",
        "btc",
        "",
        "1000000.0",
        PriceChangePercentage("7.520", DarkGreen)
    )
    CoinListComponent(
        State(
            list = listOf(coin, coin, coin),
            currentCurrency = Currency.USD,
            loadingStatus = LoadingStatus.Idle
        ),
        sendUserEvent = {},
        handleScreenEffect = {},
    )
}
