package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.domain.Currency
import com.myaxa.features.coin_list.model.ListCoinUi

internal sealed interface Event {
    sealed interface User : Event {
        data class ChangeCurrency(val currency: Currency) : User
        data object LoadInitial : User
        data object Reload : User
    }

    sealed interface System : Event {
        data class Loaded(val result: Result<List<ListCoinUi>>): System
    }
}
