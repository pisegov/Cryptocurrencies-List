package com.myaxa.features.coin_list.mvi

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

internal class Reducer @Inject constructor() {

    private val _commandFlow = MutableSharedFlow<Command>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val commandFlow = _commandFlow.asSharedFlow()

    private fun sendCommand(command: Command) = _commandFlow.tryEmit(command)

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
