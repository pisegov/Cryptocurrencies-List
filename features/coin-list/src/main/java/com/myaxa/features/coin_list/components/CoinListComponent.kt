package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.theme.DarkGreen
import com.myaxa.core.ui.throttledClickListener.throttledClickable
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage
import com.myaxa.features.coin_list.mvi.LoadingStatus
import com.myaxa.features.coin_list.mvi.State

@Composable
internal fun CoinListComponent(
    uiState: State,
    modifier: Modifier = Modifier,
    onCoinSelected: (ListCoinUi) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            uiState.list.forEach {
                item {
                    CoinComponent(
                        coin = it,
                        modifier = Modifier.throttledClickable { onCoinSelected(it) })
                }
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
        )
    )
}
