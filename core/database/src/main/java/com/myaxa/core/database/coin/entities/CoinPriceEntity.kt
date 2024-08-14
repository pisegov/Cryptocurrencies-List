package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Entity for coin price information
 *
 * @param coinId remote coin id
 * @param currency currency for the [currentPrice] equivalent
 * @param currentPrice current price in the equivalent of the [currency]
 * @param priceChangePercentage24h current price change percentage in the last 24 hours
 */
@Entity(tableName = "price", primaryKeys = ["coin_id", "currency"])
data class CoinPriceEntity(
    @ColumnInfo("coin_id") val coinId: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("current_price") val currentPrice: Double,
    @ColumnInfo("price_change") val priceChangePercentage24h: Double,
)
