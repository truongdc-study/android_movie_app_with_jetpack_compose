package com.truongdc.android.base.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import com.truongdc.android.base.screens.login.LoginViewModel
import com.truongdc.android.base.ui.theme.BlackCard
import com.truongdc.android.base.ui.theme.Yellow
import com.truongdc.android.base.utils.extensions.showToast
import com.truongdc.android.base.utils.uistate.collectEvent
import com.truongdc.android.base.utils.uistate.collectLoadingWithLifecycle
import com.truongdc.android.base.utils.uistate.collectWithLifecycle
import com.truongdc.android.core.model.User

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val view = LocalView.current
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val uiState by viewModel.collectWithLifecycle()
    val isLoading by viewModel.collectLoadingWithLifecycle()
    view.ObserverKeyBoard { isShow ->
        viewModel.onUpdateTextFiledFocus(isShow)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.collectEvent(lifecycle) { event ->
            when (event) {
                RegisterViewModel.Event.RegisterSuccess -> {
                    context.showToast("Register Success!")
                    navHostController.navigate(AppDestination.Up) {
                        popUpTo(AppDestination.Login.route) { inclusive = true }
                    }
                }

                RegisterViewModel.Event.RegisterFailed -> {
                    context.showToast("Register Failed, Please try again!")
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
                    top = if (!uiState.isTextFieldFocused) 150.dp else 50.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Account",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            BaseTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                textPlaceholder = "Mail ID",
                paddingValues = PaddingValues(top = 50.dp)
            )
            BaseTextField(
                value = uiState.name,
                onValueChange = viewModel::onNameChange,
                textPlaceholder = "Full Name",
                paddingValues = PaddingValues(top = 20.dp)
            )
            BaseTextField(
                value = uiState.pass,
                onValueChange = viewModel::onPassChange,
                textPlaceholder = "Password",
                isPassWord = true,
                paddingValues = PaddingValues(top = 20.dp)
            )
            Spacer(modifier = Modifier.size(40.dp))
            BaseButton(
                onClick = {
                    viewModel.onSubmitRegister(User(uiState.name, uiState.email, uiState.pass))
                },
                label = "Register", isEnable = !uiState.isInValid,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "LOGIN",
                fontSize = 18.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            navHostController.navigate(AppDestination.Up) {
                                popUpTo(AppDestination.Login.route) { inclusive = true }
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
    RegisterScreen()
}

