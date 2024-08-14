package com.myaxa.features.coin_list.mvi

import com.myaxa.features.coin_list.model.LoadingStatus
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * Screen [State] updater
 * Performs simple updates and sends [Command] to perform complex operations
 */
internal class Reducer @Inject constructor() {

    /**
     * Flow of [Command] to perform some complex operation
     */
    private val _commandFlow = MutableSharedFlow<Command>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val commandFlow = _commandFlow.asSharedFlow()

    private fun sendCommand(command: Command) = _commandFlow.tryEmit(command)

    /**
     * Method to update the screen [State] based on the given [Event]
     *
     * @param event state update event
     * @param state current state value
     *
     * If need to perform some complex operation, e.g. network call,
     * returns the same state and sends [Command] through [commandFlow]
     *
     * @return updated screen state
     */
    fun reduce(event: Event, state: State): State = when (event) {
        is Event.User.ChangeCurrency -> {

            sendCommand(Command.LoadList(currency = event.currency))

            state.copy(
                currentCurrency = event.currency,
                list = emptyList(),
                loadingStatus = LoadingStatus.Loading
            )
        }

        Event.User.LoadInitial -> {
            sendCommand(Command.LoadList(currency = state.currentCurrency))
            state.copy(loadingStatus = LoadingStatus.Loading, list = emptyList())
        }

        Event.User.Reload -> {
            sendCommand(Command.LoadList(currency = state.currentCurrency))
            state.copy(loadingStatus = LoadingStatus.Loading)
        }

        Event.User.SetErrorShown -> {
            state.copy(loadingStatus = LoadingStatus.Idle)
        }

        is Event.System.Loaded -> {

            val list = event.result
                .onFailure {
                    return state.copy(loadingStatus = LoadingStatus.Failure)
                }
                .getOrThrow()

            state.copy(list = list, loadingStatus = LoadingStatus.Idle)
        }
    }
}
