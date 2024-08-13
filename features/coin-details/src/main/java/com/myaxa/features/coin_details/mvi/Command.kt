package com.myaxa.features.coin_details.mvi

import com.myaxa.core.coin.domain.CoinId

internal sealed interface Command {
    data class LoadDetails(val id: CoinId) : Command
}
