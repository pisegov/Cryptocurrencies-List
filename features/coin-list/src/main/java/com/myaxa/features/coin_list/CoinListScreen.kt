package com.myaxa.features.coin_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.myaxa.core.viewmodel_inject.daggerViewModel
import com.myaxa.features.coin_list.components.CoinListContent
import com.myaxa.features.coin_list.mvi.Event

@Composable
internal fun CoinListScreen(
    viewModel: CoinListViewModel = daggerViewModel(),
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.obtainUserEvent(Event.User.LoadInitial)
    }

    CoinListContent(
        uiState = uiState,
        onCurrencySelected = { viewModel.obtainUserEvent(Event.User.ChangeCurrency(it)) }
    )
}
