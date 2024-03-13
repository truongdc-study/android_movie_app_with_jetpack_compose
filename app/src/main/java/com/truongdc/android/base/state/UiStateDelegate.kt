package com.truongdc.android.base.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface UiStateDelegate<UiState, Event> {

    val uiStateFlow: StateFlow<UiState>

    val singleEvents: Flow<Event>

    val UiStateDelegate<UiState, Event>.uiState: UiState

    suspend fun UiStateDelegate<UiState, Event>.updateUiState(
        transform: (uiState: UiState) -> UiState,
    )

    fun UiStateDelegate<UiState, Event>.asyncUpdateUiState(
        coroutineScope: CoroutineScope,
        transform: (uiState: UiState) -> UiState
    ): Job

    suspend fun UiStateDelegate<UiState, Event>.sendEvent(event: Event)
}

/**
 * @param mutexState A mutex for synchronizing state access.
 * @param initialUiState Initial UI state.
 * @param singleLiveEventCapacity Channel capacity for SingleLiveEvent.
 */
class UiStateDelegateImpl<UiState, Event>(
    initialUiState: UiState,
    singleLiveEventCapacity: Int = Channel.BUFFERED,
    private val mutexState: Mutex = Mutex()
) : UiStateDelegate<UiState, Event> {

    private val uiMutableStateFlow = MutableStateFlow(initialUiState)
    private val singleEventChannel = Channel<Event>(singleLiveEventCapacity)

    override val uiStateFlow: StateFlow<UiState>
        get() = uiMutableStateFlow.asStateFlow()

    override val singleEvents: Flow<Event>
        get() = singleEventChannel.receiveAsFlow()

    override val UiStateDelegate<UiState, Event>.uiState: UiState
        get() = uiMutableStateFlow.value

    override suspend fun UiStateDelegate<UiState, Event>.updateUiState(
        transform: (uiState: UiState) -> UiState,
    ) = mutexState.withLock {
        uiMutableStateFlow.emit(transform(uiState))
    }

    override fun UiStateDelegate<UiState, Event>.asyncUpdateUiState(
        coroutineScope: CoroutineScope,
        transform: (uiState: UiState) -> UiState
    ) = coroutineScope.launch {
        updateUiState { state -> transform(state) }
    }

    override suspend fun UiStateDelegate<UiState, Event>.sendEvent(event: Event) =
        singleEventChannel.send(event)
}