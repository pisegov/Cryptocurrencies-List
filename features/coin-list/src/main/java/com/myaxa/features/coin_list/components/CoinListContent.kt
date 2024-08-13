package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.ui.components.NetworkErrorScreenComponent
import com.myaxa.core.ui.components.ProgressIndicatorComponent
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.mvi.LoadingStatus
import com.myaxa.features.coin_list.mvi.State

@Composable
internal fun CoinListContent(
    uiState: State,
    onCurrencySelected: (Currency) -> Unit,
    onCoinSelected: (ListCoinUi) -> Unit,
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        CoinListToolbarComponent(
            currentCurrency = uiState.currentCurrency,
            onCurrencySelected = onCurrencySelected,
        )
        when {
            uiState.list.isNotEmpty() && uiState.loadingStatus is LoadingStatus.Idle -> {
                CoinListComponent(uiState = uiState, onCoinSelected = onCoinSelected)
            }

            uiState.list.isEmpty() -> when (uiState.loadingStatus) {
                LoadingStatus.Loading -> {
                    ProgressIndicatorComponent(modifier = modifier.fillMaxSize())
                }

                LoadingStatus.Idle, is LoadingStatus.Failure -> {
                    NetworkErrorScreenComponent(onRetryClicked = onRetryClicked)
                }
            }

            else -> throw IllegalStateException("Illegal screen state: $uiState")
        }
    }
}
