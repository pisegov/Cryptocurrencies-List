package com.myaxa.features.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myaxa.core.viewmodel_inject.daggerViewModel
import com.myaxa.features.coin_list.components.CoinListContent
import com.myaxa.features.coin_list.mvi.Event
import com.myaxa.features.coin_list.mvi.LoadingStatus

@Composable
internal fun CoinListScreen(
    viewModel: CoinListViewModel = daggerViewModel(),
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.obtainUserEvent(Event.User.LoadInitial)
    }

    val state = uiState
    when {
        state.list.isEmpty() && state.loadingStatus is LoadingStatus.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        state.list.isEmpty() && state.loadingStatus is LoadingStatus.Failure -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(state.loadingStatus.message)
            }
        }

        state.list.isNotEmpty() && state.loadingStatus is LoadingStatus.Idle -> {
            CoinListContent(uiState = state)
        }

        else -> {
            Text(text = state.toString())
        }
    }
}
