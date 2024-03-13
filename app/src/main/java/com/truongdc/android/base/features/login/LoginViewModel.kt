package com.truongdc.android.base.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truongdc.android.base.state.UiStateDelegate
import com.truongdc.android.base.state.UiStateDelegateImpl
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(),
    UiStateDelegate<LoginViewModel.UiState, LoginViewModel.Event> by UiStateDelegateImpl(
        UiState()
    ) {
    data class UiState(
        val title: String = "",
        val email: String = "",
        val pass: String = "",
        val isLoading: Boolean = false,
    )

    sealed interface Event {
        data object LoginSuccess: Event
        data object LoginFailed: Event
    }

    init {
        viewModelScope.launch {
            updateUiState { state -> state.copy(title = "Login Screen.") }
        }
    }

    fun onEmailChange(email: String) = viewModelScope.launch {
        updateUiState { state -> state.copy(email = email) }
    }


    fun onPassChange(pass: String) =
        asyncUpdateUiState(viewModelScope) { state -> state.copy(pass = pass) }

    fun onClickLogin() = viewModelScope.launch {
        handleLogin(email = uiState.email, pass = uiState.pass)
    }

    private suspend fun handleLogin(email: String, pass: String) {
        if (email.isNotBlank() && pass.isNotBlank()) {
            sendEvent(Event.LoginSuccess)
        } else {
            sendEvent(Event.LoginFailed)
        }
    }

    fun updateStatusLogin(status: String ) = viewModelScope.launch {
        updateUiState { state -> state.copy(title = status) }
    }
}
