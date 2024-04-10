package com.truongdc.android.base.ui.components.introduce

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
internal fun CoachMarkMessageBox(
    modifier: Modifier = Modifier,
    shape: Shape = EllipseMessageShape(),
    backgroundColor: Color = Color.White,
    messageBoxWidth: Dp? = null,
    messageBoxHeight: Dp? = null,
    contentColor: Color = Color.Black,
    content: @Composable () -> Unit
) {

    Surface(
        shape = shape, modifier = modifier
            .then(
                if (messageBoxHeight == null)
                    Modifier.height(IntrinsicSize.Min)
                else
                    Modifier.height(messageBoxHeight)
            )
            .then(
                if (messageBoxWidth == null)
                    Modifier.width(IntrinsicSize.Max)
                else
                    Modifier.width(messageBoxWidth)
            ), color = backgroundColor, contentColor = contentColor
    ) {
        content()
    }

}