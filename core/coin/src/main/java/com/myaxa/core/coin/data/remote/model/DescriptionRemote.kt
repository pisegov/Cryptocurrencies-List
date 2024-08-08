package com.myaxa.core.coin.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DescriptionRemote(
    @SerialName("en")
    val en: String,
)
