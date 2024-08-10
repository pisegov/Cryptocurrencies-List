package com.myaxa.cryptocurrencies_list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.myaxa.features.coin_list.navigation.CoinListRoute
import com.myaxa.features.coin_list.navigation.coinListDestination

@Composable
fun ApplicationNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = CoinListRoute) {

        coinListDestination()
    }
}
