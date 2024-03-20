package com.truongdc.android.base

import android.app.Application
import com.truongdc.android.core.utils.Constant
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Constant.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
