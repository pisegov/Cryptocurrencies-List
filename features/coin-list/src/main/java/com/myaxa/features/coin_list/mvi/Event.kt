package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.domain.Currency
import com.myaxa.features.coin_list.model.ListCoinUi

/**
 * An event for changing the screen [State]
 */
internal sealed interface Event {

    /**
     * UI interaction event
     */
    sealed interface User : Event {
        data class ChangeCurrency(val currency: Currency) : User
        data object LoadInitial : User
        data object Reload : User
        data object SetErrorShown : User
    }

    /**
     * Event that comes from [Actor]
     * Designed to return some complex operation result to change the screen [State]
     */
    sealed interface System : Event {
        data class Loaded(val result: Result<List<ListCoinUi>>) : System
    }
}
