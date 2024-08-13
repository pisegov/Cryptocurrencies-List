package com.myaxa.cryptocurrencies_list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.myaxa.features.coin_details.mvi.CoinDetailsEffect
import com.myaxa.features.coin_details.navigation.coinDetailsDestination
import com.myaxa.features.coin_details.navigation.navigateToCoinDetails
import com.myaxa.features.coin_list.mvi.CoinListEffect
import com.myaxa.features.coin_list.navigation.CoinListRoute
import com.myaxa.features.coin_list.navigation.coinListDestination

@Composable
fun ApplicationNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = CoinListRoute) {

        coinListDestination { effect ->
            when (effect) {
                is CoinListEffect.NavigateToCoinDetails ->
                    navController.navigateToCoinDetails(id = effect.id, name = effect.name)
            }
        }

        coinDetailsDestination { effect ->
            when (effect) {
                CoinDetailsEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }
}
