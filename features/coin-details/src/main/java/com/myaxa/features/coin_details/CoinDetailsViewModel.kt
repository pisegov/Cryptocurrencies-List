package com.myaxa.features.coin_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.features.coin_details.mvi.Actor
import com.myaxa.features.coin_details.mvi.Event
import com.myaxa.features.coin_details.mvi.Reducer
import com.myaxa.features.coin_details.mvi.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class CoinDetailsViewModel @Inject constructor(
    private val reducer: Reducer,
    private val actor: Actor,
) : ViewModel() {

    private val _state = MutableStateFlow(State.initial())
    val state = _state.asStateFlow()

    fun obtainUserEvent(event: Event.User) = reduce(event)

    private fun reduce(event: Event) = _state.update {
        reducer.reduce(event, it)
    }

    init {
        reducer.commandFlow.onEach { actor.processCommand(it) }.launchIn(viewModelScope)
        actor.systemEventFlow.onEach { reduce(event = it) }.launchIn(viewModelScope)
    }
}
