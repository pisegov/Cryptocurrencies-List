package com.myaxa.features.coin_list.mvi

import com.myaxa.core.coin.data.CoinRepository
import com.myaxa.features.coin_list.model.ListCoinUi
import com.myaxa.features.coin_list.util.ListCoinMapper
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

internal class Actor @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: ListCoinMapper,
) {
    private val _systemEventFlow = MutableSharedFlow<Event.System>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val systemEventFlow = _systemEventFlow.asSharedFlow()

    private fun sendEvent(event: Event.System) = _systemEventFlow.tryEmit(event)

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
