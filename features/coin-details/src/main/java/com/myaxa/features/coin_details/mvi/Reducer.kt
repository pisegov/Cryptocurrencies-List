package com.myaxa.features.coin_details.mvi

import com.myaxa.features.coin_details.model.LoadingStatus
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
        is Event.User.Load -> {
            sendCommand(Command.LoadDetails(event.id))
            state.copy(loadingStatus = LoadingStatus.Loading)
        }

        is Event.System.Loaded -> {
            event.result.onFailure {
                return state.copy(loadingStatus = LoadingStatus.Failure)
            }

            val model = event.result.getOrThrow()
            state.copy(loadingStatus = LoadingStatus.Success(model = model))
        }
    }
}
