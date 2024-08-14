package com.myaxa.features.coin_details.mvi

import com.myaxa.core.coin.domain.CoinId

/**
 * Command for [Actor] to perform a complex operation
 */
internal sealed interface Command {
    data class LoadDetails(val id: CoinId) : Command
}
