package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.data.CoinRepository
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.util.ListCoinMapper
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
    private val mapper: ListCoinMapper,
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

    private suspend fun sendEvent(event: Event.System) = _systemEventFlow.emit(event)

    suspend fun processCommand(command: Command) = when (command) {

        is Command.LoadList -> {

            val result: Result<List<ListCoinUi>> = repository.getCoinsList(command.currency)
                .map { list ->
                    list.map { mapper.mapToUiModel(it, command.currency) }
                }

            sendEvent(Event.System.Loaded(result = result))
        }
    }
}
