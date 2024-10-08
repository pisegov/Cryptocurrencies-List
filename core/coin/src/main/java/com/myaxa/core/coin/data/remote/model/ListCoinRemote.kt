package com.myaxa.core.coin.data.remote.model


import com.myaxa.core.coin.domain.ListCoin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Cryptocurrency in a list remote model
 *
 * @see ListCoin
 * */
@Serializable
internal data class ListCoinRemote(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("image")
    val image: String,
    @SerialName("current_price")
    val currentPrice: Double,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage: Double,
){
    fun toDomainModel(): ListCoin = ListCoin(
        id = id,
        name = name,
        symbol = symbol,
        imageUrl = image,
        currentPrice = currentPrice,
        priceChangePercentage = priceChangePercentage,
    )
}
