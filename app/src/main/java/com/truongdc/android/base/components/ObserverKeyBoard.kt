package com.truongdc.android.base.components

import android.view.View
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun View.ObserverKeyBoard(isShowKeyBoard: (Boolean) -> Unit) {
    DisposableEffect(Unit) {
        val viewTreeObserver = this@ObserverKeyBoard.viewTreeObserver
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(this@ObserverKeyBoard)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            isShowKeyBoard(isKeyboardOpen)
        }
        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}