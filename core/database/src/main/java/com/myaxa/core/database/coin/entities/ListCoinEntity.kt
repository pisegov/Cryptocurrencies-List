package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class CoinEntity(
    @ColumnInfo("coin_id") @PrimaryKey val coinId: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("symbol") val symbol: String,
    @ColumnInfo("image") val image: String,
)
