package com.truongdc.android.base.uistate

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.truongdc.android.base.state.UiStateDelegate
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <State, Event> UiStateDelegate<State, Event>.collectEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: (event: Event) -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    singleEvents.flowWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        minActiveState = lifecycleState,
    ).collect { event ->
        block.invoke(event)
    }
}