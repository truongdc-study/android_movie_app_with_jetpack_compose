package com.truongdc.android.base.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomSeekbar() {

    val positionThumb = remember {
        mutableStateOf(Offset(0f, 0f))
    }

    val thumbSize = 20.dp
    val barHeight = 80.dp
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Yellow)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    positionThumb.value = change.position
                }
            }
    ) {
        drawRoundRect(
            color = Color.LightGray,
            topLeft = Offset(0f, (size.height - barHeight.value) / 2),
            size = Size(size.width, barHeight.value),
            cornerRadius = CornerRadius(barHeight.toPx())
        )

        var pontX = 0f
        pontX = if (positionThumb.value.x < (thumbSize.value * 3)) {
            thumbSize.toPx()
        } else if (positionThumb.value.x >= (size.width - (thumbSize.value * 3)))
            size.width - thumbSize.toPx()
        else {
            positionThumb.value.x
        }

        drawCircle(
            color = Color.Blue,
            radius = thumbSize.toPx(),
            center = Offset(pontX, size.height / 2)
        )

        drawRect(
            color = Color.Blue,
            topLeft = Offset(pontX - (thumbSize.toPx()), size.height / 6),
            size = Size(thumbSize.toPx() * 2, thumbSize.toPx() * 2)
        )

        val textSize = 28.sp
        val text = "1"
        drawIntoCanvas { canvas ->
            val paint = Paint().asFrameworkPaint()
            paint.textSize = textSize.toPx()
            paint.color = Color.White.toArgb()
            canvas.nativeCanvas.drawText(
                text,
                pontX - (thumbSize.toPx() / 2),
                (size.height / 6) + 100f,
                paint
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun PreviewSeekbar() {
    CustomSeekbar()
}