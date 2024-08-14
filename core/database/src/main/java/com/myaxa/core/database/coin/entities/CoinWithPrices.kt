package com.myaxa.core.database.coin.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Combined model of [CoinEntity] and [CoinPriceEntity]
 *
 * @param coin basic coin database model
 * @param prices prices for all supported currencies
 */
data class CoinWithPrices(
    @Embedded val coin: CoinEntity,
    @Relation(
        parentColumn = "coin_id",
        entityColumn = "coin_id",
        entity = CoinPriceEntity::class,
    )
    val prices: List<CoinPriceEntity>,
)
