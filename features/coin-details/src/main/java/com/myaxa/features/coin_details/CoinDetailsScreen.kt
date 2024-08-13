package com.myaxa.features.coin_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.viewmodel_inject.daggerViewModel
import com.myaxa.features.coin_details.components.CoinDetailsContent
import com.myaxa.features.coin_details.mvi.CoinDetailsEffect
import com.myaxa.features.coin_details.mvi.Event

@Composable
internal fun CoinDetailsScreen(
    coinId: CoinId,
    coinName: String,
    effectHandler: (CoinDetailsEffect) -> Unit,
    viewModel: CoinDetailsViewModel = daggerViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.obtainUserEvent(event = Event.User.Load(coinId))
    }

    CoinDetailsContent(
        coinName = coinName,
        state = state,
        onBackClicked = { effectHandler(CoinDetailsEffect.NavigateBack) },
        onRetryClicked = { viewModel.obtainUserEvent(Event.User.Load(coinId)) },
        modifier = Modifier.fillMaxSize()
    )
}
