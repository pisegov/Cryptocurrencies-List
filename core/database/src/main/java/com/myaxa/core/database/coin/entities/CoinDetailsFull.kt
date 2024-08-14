package com.myaxa.core.database.coin.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Combined model of [CoinEntity], [CoinDescriptionEntity] and [CoinCategoryEntity]
 *
 * @param coin basic coin database model
 * @param description coin description model
 * @param categories list of categories related to coin
 */
data class CoinDetailsFull(
    @Embedded val coin: CoinEntity,

    @Relation(
        parentColumn = "coin_id",
        entityColumn = "coin_id",
        entity = CoinDescriptionEntity::class,
    )
    val description: CoinDescriptionEntity,

    @Relation(
        parentColumn = "coin_id",
        entityColumn = "category_id",
        associateBy = Junction(CoinCategoryCrossRef::class),
    )
    val categories: List<CoinCategoryEntity>,
)
