package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.DayNightPreview
import com.myaxa.core.ui.theme.DarkGreen
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.model.PriceChangePercentage
import com.myaxa.features.coin_list.mvi.LoadingStatus
import com.myaxa.features.coin_list.mvi.State

@Composable
internal fun CoinListContent(
    uiState: State,
    modifier: Modifier = Modifier,
) {
    val bottomWindowInsets=
        WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
    val horizontalNavBarSize=
        WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottomWindowInsets.union(horizontalNavBarSize).asPaddingValues())
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = WindowInsets.systemBars.only(WindowInsetsSides.Top).asPaddingValues(),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            uiState.list.forEach {
                item { CoinComponent(coin = it) }
            }
        }
    }
}

@DayNightPreview
@Composable
private fun CoinListContentPreview() {
    val coin = ListCoinUi(
        "bitcoin",
        "Bitcoin",
        "btc",
        "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        "1000000.0",
        PriceChangePercentage("7.520", DarkGreen)
    )
    CoinListContent(
        State(
            list = listOf(coin, coin, coin),
            currentCurrency = Currency.USD,
            loadingStatus = LoadingStatus.Idle
        )
    )
}
