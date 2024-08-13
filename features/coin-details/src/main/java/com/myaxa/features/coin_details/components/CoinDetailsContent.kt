package com.myaxa.features.coin_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myaxa.core.ui.components.NetworkErrorScreenComponent
import com.myaxa.core.ui.components.ProgressIndicatorComponent
import com.myaxa.features.coin_details.model.CoinDetailsUi
import com.myaxa.features.coin_details.model.LoadingStatus
import com.myaxa.features.coin_details.mvi.State

@Composable
internal fun CoinDetailsContent(
    coinName: String,
    state: State,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    onRetryClicked: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ) {
        CoinDetailsToolbar(
            title = coinName,
            onBackClicked = onBackClicked,
            modifier = Modifier
                .fillMaxWidth()
        )

        when (state.loadingStatus) {
            is LoadingStatus.Success -> {
                CoinDetailsComponent(model = state.loadingStatus.model)
            }

            LoadingStatus.Loading -> {
                ProgressIndicatorComponent(modifier = modifier.fillMaxSize())
            }

            LoadingStatus.Failure -> {
                NetworkErrorScreenComponent(onRetryClicked = onRetryClicked)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsContentPreview() {
    val model = CoinDetailsUi(
        id = "bitcoin",
        name = "Bitcoin",
        imageUrl = "",
        description = descriptionText,
        categories = categoriesText,
    )
    CoinDetailsContent(
        coinName = model.name,
        state = State(loadingStatus = LoadingStatus.Success(model)),
    )
}

private const val descriptionText =
    """Bitcoin is a decentralized cryptocurrency originally described in a 2008 whitepaper by a person, or group of people, using the alias Satoshi Nakamoto. It was launched soon after, in January 2009.
        
Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”"""

private const val categoriesText = """Smart Contract Platform, Ethereum Ecosystems"""
