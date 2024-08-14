package com.myaxa.core.coin.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Cryptocurrency description remote model
 */
@Serializable
internal data class DescriptionRemote(
    @SerialName("en")
    val en: String,
)
