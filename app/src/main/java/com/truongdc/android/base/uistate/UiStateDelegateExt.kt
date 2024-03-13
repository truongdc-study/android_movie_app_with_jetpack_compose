package com.truongdc.android.base.uistate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import com.truongdc.android.base.state.UiStateDelegate
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <R> UiStateDelegate<R, *>.collectUiState() = this.uiStateFlow.collectAsState()

@Composable
fun <R> UiStateDelegate<R, *>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) = this.uiStateFlow.collectAsStateWithLifecycle(
    minActiveState = minActiveState,
)
