package com.myaxa.features.coin_list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.myaxa.features.coin_list.CoinListScreen
import com.myaxa.features.coin_list.mvi.CoinListEffect
import kotlinx.serialization.Serializable

@Serializable
data object CoinListRoute

fun NavGraphBuilder.coinListDestination(effectHandler: (CoinListEffect) -> Unit) {
    composable<CoinListRoute> { navBackStackEntry ->
        CoinListScreen(effectHandler = effectHandler)
    }
}
