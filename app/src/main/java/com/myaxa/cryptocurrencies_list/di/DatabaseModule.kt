package com.myaxa.cryptocurrencies_list.di

import android.app.Application
import com.myaxa.core.database.CryptocurrenciesListDatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabaseProvider(applicationContext: Application) =
        CryptocurrenciesListDatabaseProvider(applicationContext)
}
