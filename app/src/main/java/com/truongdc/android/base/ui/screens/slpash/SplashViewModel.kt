package com.truongdc.android.base.ui.screens.slpash

import androidx.lifecycle.ViewModel
import com.truongdc.android.base.data.local.datastores.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) : ViewModel() {

    fun getIsLogin() = preferencesDataStore.isLogIn
}