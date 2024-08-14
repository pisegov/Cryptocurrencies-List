package com.myaxa.core.coin.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Cryptocurrency logo image remote model
 */
@Serializable
internal data class ImageRemote(
    @SerialName("large")
    val large: String,
    @SerialName("small")
    val small: String,
    @SerialName("thumb")
    val thumb: String
)
