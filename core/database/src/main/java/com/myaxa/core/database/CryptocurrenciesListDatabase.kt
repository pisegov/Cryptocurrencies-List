package com.myaxa.core.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myaxa.core.database.coin.CoinDao
import com.myaxa.core.database.coin.entities.CoinCategoryCrossRef
import com.myaxa.core.database.coin.entities.CoinCategoryEntity
import com.myaxa.core.database.coin.entities.CoinDescriptionEntity
import com.myaxa.core.database.coin.entities.CoinEntity
import com.myaxa.core.database.coin.entities.CoinPriceEntity


@Database(
    entities = [
        CoinEntity::class,
        CoinPriceEntity::class,
        CoinDescriptionEntity::class,
        CoinCategoryEntity::class,
        CoinCategoryCrossRef::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class CryptocurrenciesListDatabase : RoomDatabase() {
    abstract val coinDao: CoinDao
}

class CryptocurrenciesListDatabaseProvider(applicationContext: Application) {
    val db = Room.databaseBuilder(
        context = applicationContext,
        klass = CryptocurrenciesListDatabase::class.java,
        name = "cryptocurrencies"
    ).build()
}
