package com.truongdc.android.core.utils.logs

import android.util.Log
import com.truongdc.android.core.utils.Constant
import timber.log.Timber

object LogUtils {
    fun d(tag: String, message: String) {
        if (Constant.DEBUG) {
            Timber.d(tag, message)
        }
    }

    fun e(tag: String, msg: String) {
        if (Constant.DEBUG) {
            Timber.e(tag, msg)
        }
    }

    fun e(tag: String, msg: String, throwable: Throwable) {
        if (Constant.DEBUG) {
            Timber.e(tag, msg, throwable)
        }
    }

    fun thread() {
        if (Constant.DEBUG) {
            Timber.e("THREAD", "${Thread.currentThread().name} has run.")
        }
    }
}
