package com.truongdc.android.base.features.components

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun BackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    val dispatcherOwner = LocalOnBackPressedDispatcherOwner.current ?: error("No Back Dispatcher Owner provided")
    val dispatcher = rememberUpdatedState(dispatcherOwner.onBackPressedDispatcher)
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(dispatcher.value, lifecycleOwner) {
        val callback = object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }

        dispatcher.value.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}