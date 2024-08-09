package com.myaxa.core.database.coin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myaxa.core.database.coin.entities.CoinCategoryCrossRef
import com.myaxa.core.database.coin.entities.CoinCategoryEntity
import com.myaxa.core.database.coin.entities.CoinDescriptionEntity
import com.myaxa.core.database.coin.entities.CoinDetailsFull
import com.myaxa.core.database.coin.entities.CoinEntity
import com.myaxa.core.database.coin.entities.CoinPriceEntity
import com.myaxa.core.database.coin.entities.CoinWithPrices

@Dao
interface CoinDao {

    @Transaction
    @Query("select * from coin")
    suspend fun getCoinsWithPrices(): List<CoinWithPrices>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoins(items: List<CoinEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPrices(items: List<CoinPriceEntity>)

    @Transaction
    @Query("select * from coin where coin_id=:id")
    suspend fun getCoinDetails(id: String): CoinDetailsFull

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoinDescription(item: CoinDescriptionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoinCategories(items: List<CoinCategoryEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoinToCategoriesCrossRefs(items: List<CoinCategoryCrossRef>)
}
