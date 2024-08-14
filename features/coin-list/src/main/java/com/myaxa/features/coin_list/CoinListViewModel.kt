package com.myaxa.features.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.features.coin_list.mvi.Actor
import com.myaxa.features.coin_list.mvi.Event
import com.myaxa.features.coin_list.mvi.Reducer
import com.myaxa.features.coin_list.mvi.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Responsible for saving the screen state and processing events to update it
 * @param reducer updates screen state
 * @param actor performs complex operations
 */
internal class CoinListViewModel @Inject constructor(
    private val reducer: Reducer,
    private val actor: Actor,
) : ViewModel() {

    private val _state = MutableStateFlow(State.initial())
    val state = _state.asStateFlow()

    fun obtainUserEvent(event: Event.User) = reduce(event)

    private fun reduce(event: Event) = _state.update {
        reducer.reduce(event = event, state = it)
    }

    init {
        // Connects events flow from [Actor] to [Reducer]
        reducer.commandFlow.onEach { actor.processCommand(command = it) }.launchIn(viewModelScope)
        // Connects commands flow from [Reducer] to [Actor]
        actor.systemEventFlow.onEach { reduce(event = it) }.launchIn(viewModelScope)
    }
}
