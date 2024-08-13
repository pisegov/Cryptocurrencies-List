package com.myaxa.features.coin_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.features.coin_details.CoinDetailsScreen
import com.myaxa.features.coin_details.mvi.CoinDetailsEffect
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsRoute(val coinId: CoinId, val coinName: String)

fun NavGraphBuilder.coinDetailsDestination(
    effectHandler: (CoinDetailsEffect) -> Unit
) {
    composable<CoinDetailsRoute> { navBackStackEntry ->
        val arguments = navBackStackEntry.toRoute<CoinDetailsRoute>()

        CoinDetailsScreen(
            coinId = arguments.coinId,
            coinName = arguments.coinName,
            effectHandler = effectHandler,
        )
    }
}

fun NavController.navigateToCoinDetails(id: CoinId, name: String) {
    navigate(CoinDetailsRoute(coinId = id, coinName = name))
}
