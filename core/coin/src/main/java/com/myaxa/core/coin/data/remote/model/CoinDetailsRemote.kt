package com.myaxa.core.coin.data.remote.model


import com.myaxa.core.coin.domain.CoinDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Cryptocurrency details remote model
 *
 * @see CoinDetails
 */
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
    fun toDomainModel(): CoinDetails = CoinDetails(
        id = id,
        name = name,
        imageUrl = image.small,
        description = description.en,
        categories = categories
    )
}
