package com.myaxa.core.coin.data.local

import androidx.room.withTransaction
import com.myaxa.core.coin.domain.CoinDetails
import com.myaxa.core.coin.domain.CoinId
import com.myaxa.core.coin.domain.Currency
import com.myaxa.core.coin.domain.ListCoin
import com.myaxa.core.database.CryptocurrenciesListDatabase
import com.myaxa.core.database.CryptocurrenciesListDatabaseProvider
import com.myaxa.core.database.coin.entities.CoinCategoryCrossRef
import com.myaxa.core.database.coin.entities.CoinCategoryEntity
import com.myaxa.core.database.coin.entities.CoinDescriptionEntity
import com.myaxa.core.database.coin.entities.CoinDetailsFull
import com.myaxa.core.database.coin.entities.CoinEntity
import com.myaxa.core.database.coin.entities.CoinPriceEntity
import com.myaxa.core.database.coin.entities.CoinWithPrices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun CoinLocalDataSource(
    databaseProvider: CryptocurrenciesListDatabaseProvider,
): CoinLocalDataSource = CoinLocalDataSource(databaseProvider.db)

class CoinLocalDataSource internal constructor(
    private val db: CryptocurrenciesListDatabase,
) {
    private val coinDao get() = db.coinDao
    suspend fun insertCoins(items: List<ListCoin>, currency: Currency) =
        withContext(Dispatchers.IO) {
            val coins: List<CoinEntity> = items.map { it.toCoinEntity() }
            val prices = items.map { it.toCoinPrices(currency) }
            db.withTransaction {
                coinDao.insertCoins(coins)
                coinDao.insertPrices(prices)
            }
        }

    suspend fun getCoinListWithPrices(): List<CoinWithPrices> =
        withContext(Dispatchers.IO) {
            coinDao.getCoinsWithPrices()
        }

    suspend fun insertCoinDetails(item: CoinDetails) = withContext(Dispatchers.IO) {
        val description = CoinDescriptionEntity(item.id, item.description)
        val categoryNames = item.categories
        val categories = categoryNames.map { CoinCategoryEntity(name = it) }

        db.withTransaction {
            with(coinDao) {
                insertCoinDescription(description)
                insertCoinCategories(categories)
                val crossRefs = getCategoryIds(categoryNames)
                    .map { categoryId -> CoinCategoryCrossRef(item.id, categoryId) }
                insertCoinToCategoriesCrossRefs(crossRefs)
            }
        }
    }

    suspend fun getCoinDetails(id: CoinId): CoinDetailsFull = withContext(Dispatchers.IO) {
        coinDao.getCoinDetails(id)
    }
}

internal fun ListCoin.toCoinEntity() = CoinEntity(
    coinId = id,
    name = name,
    symbol = symbol,
    image = image
)

internal fun ListCoin.toCoinPrices(currency: Currency) = CoinPriceEntity(
    coinId = id,
    currency = currency.name,
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage,
)
