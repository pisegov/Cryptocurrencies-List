package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.domain.Currency

internal sealed interface Command {
    data class LoadList(val currency: Currency) : Command
}
