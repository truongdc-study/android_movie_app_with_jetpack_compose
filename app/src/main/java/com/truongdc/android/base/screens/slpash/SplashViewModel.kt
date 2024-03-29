package com.truongdc.android.base.screens.slpash

import androidx.lifecycle.ViewModel
import com.truongdc.android.core.source.local.datastores.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) : ViewModel() {

    fun getIsLogin() = preferencesDataStore.isLogIn
}