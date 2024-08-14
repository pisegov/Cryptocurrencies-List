package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Basic coin database entity
 *
 * @param cacheId database coin id
 * @param coinId remote coin id
 * @param name coin title
 * @param symbol currency code
 * @param image: url for the coin logo image
 */
@Entity(
    tableName = "coin",
    indices = [Index(value = ["coin_id"], unique = true)]
)
data class CoinEntity(
    @ColumnInfo("cache_id") @PrimaryKey(autoGenerate = true) val cacheId: Long = 0,
    @ColumnInfo("coin_id") val coinId: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("symbol") val symbol: String,
    @ColumnInfo("image") val image: String,
)
