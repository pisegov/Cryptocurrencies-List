package com.myaxa.features.coin_list.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.components.PullToRefreshLazyColumnComponent
import com.myaxa.core.ui.theme.DarkGreen
import com.myaxa.core.ui.throttledClickListener.throttledClickable
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage
import com.myaxa.features.coin_list.mvi.CoinListEffect
import com.myaxa.features.coin_list.mvi.Event
import com.myaxa.features.coin_list.mvi.LoadingStatus
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
    val context = LocalContext.current
    LaunchedEffect(uiState) {
        if (uiState.isRefreshFailure()) {
            Toast.makeText(context, "Произошла ошибка при загрузке", Toast.LENGTH_SHORT).show()
            sendUserEvent(Event.User.SetErrorShown)
        }
    }

    PullToRefreshLazyColumnComponent(
        isRefreshing = uiState.isRefreshing(),
        onRefresh = { sendUserEvent(Event.User.Reload) },
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
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
