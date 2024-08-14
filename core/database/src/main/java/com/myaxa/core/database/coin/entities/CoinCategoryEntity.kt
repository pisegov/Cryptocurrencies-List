package com.myaxa.core.database.coin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity for a coin category
 * Refers to the [CoinEntity] in a many-to-many relation
 */
@Entity(
    tableName = "category",
    indices = [Index(value = ["name"], unique = true)],
)
data class CoinCategoryEntity(
    @ColumnInfo("category_id") @PrimaryKey(autoGenerate = true) val categoryId: Long = 0L,
    @ColumnInfo("name") val name: String,
)


/**
 * Auxiliary entity to maintain a many-to-many relationship
 * between the [CoinEntity] and [CoinCategoryEntity] tables.
 */
@Entity(
    tableName = "coin_to_category",
    primaryKeys = ["coin_id", "category_id"],
    indices = [Index(value = ["category_id"], unique = false)]
)
data class CoinCategoryCrossRef(
    @ColumnInfo("coin_id") val coinId: String,
    @ColumnInfo("category_id") val categoryId: Long,
)
