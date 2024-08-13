package com.myaxa.features.coin_details.mvi

import com.myaxa.core.coin.domain.CoinId
import com.myaxa.features.coin_details.model.CoinDetailsUi

internal sealed interface Event {
    sealed interface User : Event {
        data class Load(val id: CoinId) : User
    }

    sealed interface System : Event {
        data class Loaded(val result: Result<CoinDetailsUi>) : System
    }
}
