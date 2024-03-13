package com.truongdc.android.base.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.truongdc.android.base.uistate.collectEvent
import com.truongdc.android.base.uistate.collectWithLifecycle
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel = LoginViewModel()) {
    val lifecycle = LocalLifecycleOwner.current
    val uiState by viewModel.collectWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.collectEvent(lifecycle) { event ->
            when (event) {
                LoginViewModel.Event.LoginSuccess -> {
                    viewModel.updateStatusLogin("Login Success!")
                }
                LoginViewModel.Event.LoginFailed -> {
                    viewModel.updateStatusLogin("Login Failed!")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(all = 14.dp)
    ) {

        Text(
            text = uiState.title,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = uiState.email,
            placeholder = {
                Text(text = "Enter your email:")
            },
            onValueChange = {
                viewModel.onEmailChange(it)
            }
        )

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = uiState.pass,
            placeholder = {
                Text(text = "Enter your pass:")
            },
            onValueChange = viewModel::onPassChange
        )

        Button(onClick = {
            viewModel.onClickLogin()
        }, modifier = Modifier.padding(top = 10.dp)) {
            Text(text = "Login")
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun LoginPreView() {
    LoginScreen()
}