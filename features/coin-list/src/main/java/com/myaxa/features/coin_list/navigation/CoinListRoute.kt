package com.myaxa.features.coin_list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.myaxa.features.coin_list.CoinListScreen
import kotlinx.serialization.Serializable

@Serializable
data object CoinListRoute

fun NavGraphBuilder.coinListDestination() {
    composable<CoinListRoute> { navBackStackEntry ->
        CoinListScreen()
    }
}
