package com.myaxa.core.coin.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.myaxa.core.coin.domain.CoinDetails as CoinDetails1

@Serializable
internal data class CoinDetailsRemote(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: ImageRemote,
    @SerialName("description")
    val description: DescriptionRemote,
    @SerialName("categories")
    val categories: List<String>,
) {
    fun toDomainModel(): CoinDetails1 = CoinDetails1(
        id = id,
        name = name,
        image = image.small,
        description = description.en,
        categories = categories
    )
}
