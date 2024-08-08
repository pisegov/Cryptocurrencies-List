package com.myaxa.cryptocurrencies_list.di

import com.myaxa.cryptocurrencies_list.BuildConfig
import com.myaxa.network.NetworkClientProvider
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json

@Module
class NetworkModule {
    @Provides
    fun provideJson(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun provideNetworkClientProvider(json: Json): NetworkClientProvider {
        return NetworkClientProvider(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY,
            json = json,
        )
    }
}
