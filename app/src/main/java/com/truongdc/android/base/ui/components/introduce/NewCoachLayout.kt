package com.truongdc.android.base.ui.components.introduce

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt


@Composable
fun NewCoachLayout(
    canvasRect: Rect,
    targetBound: Rect,
    alignment: Alignment = Alignment.BottomCenter,
    isForcedAlignment: Boolean = false,
    content: @Composable () -> Unit
) {

    SubcomposeLayout { constraints ->
        val mainPlaceable = subcompose(Unit, content).map {
            it.measure(constraints)
        }
        val contentSize = mainPlaceable.fold(IntSize.Zero) { currentMax, placeable ->
            IntSize(
                width = maxOf(currentMax.width, placeable.width),
                height = maxOf(currentMax.height, placeable.height)
            )
        }
        val offset = if (isForcedAlignment) {
            findOffset(targetBound, contentSize, alignment)
        } else {
            val upperRectangle = Rect(
                top = 0f,
                bottom = targetBound.topRight.y,
                right = canvasRect.right,
                left = targetBound.left
            )
            val bottomRectangle = Rect(
                top = targetBound.bottomLeft.y,
                bottom = canvasRect.bottom,
                right = canvasRect.right,
                left = canvasRect.left
            )
            if (upperRectangle.height >= bottomRectangle.height) {
                if (contentSize.height <= upperRectangle.height)
                    Offset(0f, targetBound.top - contentSize.height)
                else
                    upperRectangle.topLeft
            } else {
                bottomRectangle.topLeft
            }
        }

        layout(width = canvasRect.width.roundToInt(), canvasRect.height.roundToInt()) {
            mainPlaceable.forEach {
                it.placeRelative(0, offset.y.toInt())
            }
        }
    }
}

private fun findOffset(
    targetBound: Rect,
    contentSize: IntSize,
    alignment: Alignment,
): Offset {
    return when (alignment) {
        Alignment.TopStart -> {
            Offset(
                x = targetBound.topLeft.x - contentSize.width.toFloat(),
                y = targetBound.topLeft.y - contentSize.height.toFloat()
            )
        }

        Alignment.TopCenter -> {
            Offset(
                x = targetBound.center.x - contentSize.width / 2f,
                y = targetBound.center.y - targetBound.height - contentSize.height.toFloat()
            )
        }

        Alignment.TopEnd -> {
            Offset(
                x = targetBound.topRight.x,
                y = targetBound.topRight.y - contentSize.height.toFloat()
            )
        }

        Alignment.CenterStart -> {
            Offset(
                x = targetBound.center.x - targetBound.width / 2f - contentSize.width,
                y = targetBound.center.y - contentSize.height / 2,
            )
        }

        Alignment.CenterEnd -> {
            Offset(
                x = targetBound.center.x + targetBound.width / 2f,
                y = targetBound.center.y - contentSize.height / 2,
            )
        }

        Alignment.Center -> {
            Offset(
                x = targetBound.center.x - contentSize.width / 2f,
                y = targetBound.center.y
            )
        }

        Alignment.BottomStart -> {
            Offset(
                x = targetBound.bottomLeft.x - contentSize.width,
                y = targetBound.bottomLeft.y,
            )
        }

        Alignment.BottomCenter -> {
            Offset(
                x = targetBound.bottomCenter.x,
                y = targetBound.bottomCenter.y,
            )
        }

        Alignment.BottomEnd -> {
            Offset(
                x = targetBound.bottomRight.x,
                y = targetBound.bottomRight.y,
            )
        }

        else -> {
            Offset(0f, 0f)
        }
    }

}