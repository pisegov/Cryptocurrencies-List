package com.myaxa.features.coin_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myaxa.core.coin.domain.Currency
import com.myaxa.features.coin_list.mvi.LoadingStatus
import com.myaxa.features.coin_list.mvi.State

@Composable
internal fun CoinListContent(
    uiState: State,
    onCurrencySelected: (Currency) -> Unit,
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
                CoinListComponent(uiState = uiState)
            }

            uiState.list.isEmpty() && uiState.loadingStatus is LoadingStatus.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.list.isEmpty() && uiState.loadingStatus is LoadingStatus.Failure -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(uiState.loadingStatus.message)
                }
            }

            else -> throw IllegalStateException("Illegal screen state: $uiState")
        }
    }
}
