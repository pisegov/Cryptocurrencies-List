package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "description")
data class CoinDescriptionEntity(
    @ColumnInfo("coin_id") @PrimaryKey val coinId: String,
    @ColumnInfo("description") val text: String,
)
