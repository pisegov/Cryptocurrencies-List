package com.myaxa.cryptocurrencies_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.myaxa.core.ui.theme.CryptocurrenciesListTheme
import com.myaxa.cryptocurrencies_list.navigation.ApplicationNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrenciesListTheme {
                val navController = rememberNavController()
                Surface {
                    ApplicationNavigation(navController = navController)
                }
            }
        }
    }
}
