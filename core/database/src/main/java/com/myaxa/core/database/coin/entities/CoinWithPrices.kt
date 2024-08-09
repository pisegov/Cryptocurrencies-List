package com.myaxa.core.database.coin.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CoinWithPrices(
    @Embedded val coin: CoinEntity,
    @Relation(
        parentColumn = "coin_id",
        entityColumn = "coin_id",
        entity = CoinPriceEntity::class,
    )
    val prices: List<CoinPriceEntity>,
)
