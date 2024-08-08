package com.myaxa.core.coin.data.remote.model


import com.myaxa.core.coin.domain.ListCoin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val priceChangePercentage24h: Double,
){
    fun toDomainModel(): ListCoin = ListCoin(
        id = id,
        name = name,
        symbol = symbol,
        image = image,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage24h,
    )
}
