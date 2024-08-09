package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "price", primaryKeys = ["coin_id", "currency"])
data class CoinPriceEntity(
    @ColumnInfo("coin_id") val coinId: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("current_price") val currentPrice: Double,
    @ColumnInfo("price_change") val priceChangePercentage24h: Double,
)
