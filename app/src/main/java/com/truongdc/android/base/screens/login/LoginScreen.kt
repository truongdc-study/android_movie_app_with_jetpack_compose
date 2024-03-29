package com.truongdc.android.base.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.components.BaseButton
import com.truongdc.android.base.components.BaseTextField
import com.truongdc.android.base.components.LoadingContent
import com.truongdc.android.base.components.ObserverKeyBoard
import com.truongdc.android.base.navigation.AppDestination
import com.truongdc.android.base.navigation.navigate
import com.truongdc.android.base.ui.theme.Yellow
import com.truongdc.android.base.utils.extensions.showToast
import com.truongdc.android.base.utils.uistate.collectEvent
import com.truongdc.android.base.utils.uistate.collectLoadingWithLifecycle
import com.truongdc.android.base.utils.uistate.collectWithLifecycle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val view = LocalView.current
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val uiState by viewModel.collectWithLifecycle()
    val isLoading by viewModel.collectLoadingWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    view.ObserverKeyBoard { isShow ->
        viewModel.onUpdateTextFiledFocus(isShow)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.collectEvent(lifecycle) { event ->
            when (event) {
                LoginViewModel.Event.LoginSuccess -> {
                    context.showToast("Login Success!")
                    navHostController.navigate(AppDestination.MovieList) {
                        popUpTo(AppDestination.Login.route) { inclusive = true }
                    }
                }

                LoginViewModel.Event.LoginFailed -> {
                    context.showToast("Login Failed, Please try again!")
                }
            }
        }
    }
    LoadingContent(isLoading = isLoading) {
        Column(
            modifier = Modifier
                .background(Yellow)
                .fillMaxSize()
                .padding(
                    top = if (!uiState.isTextFieldFocused) 150.dp else 80.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome Back!",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                text = "Login to continue",
                fontSize = 16.sp,
                color = Color.White
            )
            BaseTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                textPlaceholder = "Mail ID",
                paddingValues = PaddingValues(top = 50.dp)
            )
            BaseTextField(
                value = uiState.pass,
                onValueChange = viewModel::onPassChange,
                textPlaceholder = "Password",
                isPassWord = true,
                paddingValues = PaddingValues(top = 20.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Forget Password?",
                fontSize = 13.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = Modifier.size(30.dp))
            BaseButton(
                label = "Login",
                isEnable = !uiState.isInValid,
                paddingValues = PaddingValues(top = 8.dp, bottom = 8.dp),
                onClick = {
                    keyboardController?.hide()
                    viewModel.onSubmitLogin(uiState.email, uiState.pass)
                })
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "CREATE ACCOUNT",
                fontSize = 18.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            navHostController.navigate(AppDestination.Register) {
                                popUpTo(AppDestination.Splash.route) { inclusive = true }
                            }
                        })
                    },
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun Preview() {
    LoginScreen()
}