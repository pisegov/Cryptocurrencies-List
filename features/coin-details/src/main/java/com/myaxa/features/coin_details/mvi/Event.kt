package com.myaxa.features.coin_details.mvi

import com.myaxa.core.coin.domain.CoinId
import com.myaxa.features.coin_details.model.CoinDetailsUi

/**
 * An event for changing the screen [State]
 */
internal sealed interface Event {

    /**
     * UI interaction event
     */
    sealed interface User : Event {
        data class Load(val id: CoinId) : User
    }

    /**
     * Event that comes from [Actor]
     * Designed to return some complex operation result to change the screen [State]
     */
    sealed interface System : Event {
        data class Loaded(val result: Result<CoinDetailsUi>) : System
    }
}
