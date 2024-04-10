package com.truongdc.android.base.ui.components.introduce

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.absoluteValue

class CircleRevealEffect : RevealEffect {

    private val radius: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val focus: Animatable<Float, AnimationVector1D> = Animatable(1f)
    private val animationSpec = infiniteRepeatable<Float>(
        animation = tween(2000, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Restart
    )

    override suspend fun enterAnimation(targetBounds: Rect) {
        radius.snapTo(0f)
        radius.animateTo(targetBounds.maxDimension / 2f + 40f, tween(500, easing = LinearEasing))
        focus.snapTo(1f)
        focus.animateTo(targetValue = 0.5f, animationSpec = animationSpec)
    }

    override suspend fun exitAnimation(targetBounds: Rect) {
        focus.animateTo(0f, animationSpec = tween(500, easing = LinearEasing))
        radius.animateTo(0f, tween(500, easing = LinearEasing))
    }

    override fun drawTargetShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        drawScope.apply {
            drawCircle(
                color = Color.Blue,
                radius = targetBounds.maxDimension.absoluteValue * focus.value * 2f,
                center = targetBounds.center,
                alpha = 1 - focus.value,
                blendMode = BlendMode.Overlay
            )
            drawCircle(
                color = Color.White,
                radius = radius.value,
                center = targetBounds.center,
                blendMode = BlendMode.Clear
            )
        }
        return Rect(
            offset = Offset(
                x = targetBounds.topLeft.x - 40f,
                y = targetBounds.topLeft.y - targetBounds.maxDimension / 2f
            ),
            size = Size(
                width = targetBounds.maxDimension + 80f,
                height = targetBounds.maxDimension + 80f
            )
        )
    }


}

class RectangleRevealEffect : RevealEffect {
    private val rect = Animatable(Rect(offset = Offset.Zero, size = Size.Zero), rectToVector)

    override suspend fun enterAnimation(targetBounds: Rect) {
        val x = targetBounds.topLeft.x - 50f
        val y = targetBounds.topLeft.y - 50f
        val newOffset = Offset(x, y)
        val height = targetBounds.size.height + 100f
        val width = targetBounds.size.width + 100f
        val newSize = Size(width, height)
        val newBound = Rect(newOffset, newSize)

        rect.snapTo(Rect(targetBounds.center, size = Size.Zero))
        rect.animateTo(newBound, tween(500, easing = LinearEasing))

    }

    override suspend fun exitAnimation(targetBounds: Rect) {
        rect.animateTo(
            Rect(targetBounds.center, size = Size.Zero),
            tween(500, easing = LinearEasing)
        )
    }

    override fun drawTargetShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        val x = targetBounds.topLeft.x - 50f
        val y = targetBounds.topLeft.y - 50f
        val newOffset = Offset(x, y)
        val height = targetBounds.size.height + 100f
        val width = targetBounds.size.width + 100f
        val newSize = Size(width, height)
        val newBound = Rect(newOffset, newSize)
        drawScope.apply {
            drawRect(
                color = Color.White,
                size = rect.value.size,
                blendMode = BlendMode.Clear,
                topLeft = rect.value.topLeft
            )
        }
        return newBound
    }
}

class CanopasRevealEffect : RevealEffect {
    private val radius: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val outerRadius: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val outerAlphaAnim: Animatable<Float, AnimationVector1D> = Animatable(0.6f)
    private val animationSpec = infiniteRepeatable<Float>(
        animation = tween(2000, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Restart
    )

    override suspend fun enterAnimation(targetBounds: Rect) {
        radius.snapTo(0f)
        radius.animateTo(targetBounds.maxDimension / 2f + 40f, tween(500, easing = LinearEasing))

        outerRadius.snapTo(targetBounds.maxDimension)
        outerRadius.animateTo(targetBounds.maxDimension * 2f, animationSpec = animationSpec)
        outerAlphaAnim.animateTo(targetValue = 0f, animationSpec = animationSpec)

    }

    override suspend fun exitAnimation(targetBounds: Rect) {
        radius.animateTo(0f, tween(500, easing = LinearEasing))
        outerRadius.animateTo(0f, tween(500, easing = LinearEasing))
    }

    override fun drawTargetShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        drawScope.apply {
            drawCircle(
                color = Color.Blue,
                center = targetBounds.center,
                radius = outerRadius.value,
                alpha = outerAlphaAnim.value
            )
            drawCircle(
                color = Color.White,
                radius = radius.value,
                center = targetBounds.center,
                blendMode = BlendMode.Xor
            )
        }
        return Rect(
            offset = Offset(
                x = targetBounds.topLeft.x - 40f,
                y = targetBounds.topLeft.y - targetBounds.maxDimension / 2
            ),
            size = Size(
                width = targetBounds.maxDimension + 80f,
                height = targetBounds.maxDimension + 80f
            )
        )
    }


}
