package com.myaxa.features.coin_details.mvi

import com.myaxa.core.coin.data.CoinRepository
import com.myaxa.features.coin_details.model.toUiModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * Complex operation processor
 * Receives [Command] from [Reducer] to perform operation
 * Sends [Event.System] to [Reducer] to update the [State] with operation result
 */
internal class Actor @Inject constructor(
    private val repository: CoinRepository,
) {

    /**
     * Flow of [Event.System] to return complex operation result
     */
    private val _systemEventFlow = MutableSharedFlow<Event.System>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val systemEventFlow = _systemEventFlow.asSharedFlow()

    private suspend fun sendSystemEvent(event: Event.System) = _systemEventFlow.emit(event)

    suspend fun processCommand(command: Command) = when (command) {
        is Command.LoadDetails -> {
            val result = repository.getCoinDetails(command.id)
                .map { it.toUiModel() }
            sendSystemEvent(Event.System.Loaded(result = result))
        }
    }
}
