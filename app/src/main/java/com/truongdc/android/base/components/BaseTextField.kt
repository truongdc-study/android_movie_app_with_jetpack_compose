package com.truongdc.android.base.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.truongdc.android.base.ui.theme.Yellow

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textPlaceholder: String?,
    isPassWord: Boolean = false,
    maxLines: Int = 1,
    paddingValues: PaddingValues = PaddingValues()
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        value = value, onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 18.sp),
        placeholder = {
            textPlaceholder?.let {
                Text(text = it, color = Color.White)
            }
        },
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Yellow,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            disabledIndicatorColor = Color.White,
            textColor = Color.Black
        ),
        visualTransformation = if (isPassWord) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
    )
}