package com.truongdc.android.base.ui.components.introduce

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.absoluteValue


class CombinedRevealAndCoachStyle(
    private val color: Color,
    private val alpha: Float
) : CoachStyle, RevealEffect {
    override val backGroundColor: Color
        get() = color
    override val backgroundAlpha: Float
        get() = alpha
    private val radius: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val outerRadius: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val outerAlphaAnim: Animatable<Float, AnimationVector1D> = Animatable(0f)
    private val animationSpec = infiniteRepeatable<Float>(
        animation = tween(500, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Restart
    )

    @Composable
    override fun drawCoachButtons(
        contentScope: BoxWithConstraintsScope,
        targetBounds: Rect,
        onBack: () -> Unit,
        onSkip: () -> Unit,
        onNext: () -> Unit
    ) {
        contentScope.apply {
            Button(onClick = {
                onSkip()
            }, modifier = Modifier.align(Alignment.BottomStart)) {
                Text(text = "Skip")
            }
            Button(onClick = {
                onNext()
            }, modifier = Modifier.align(Alignment.BottomEnd)) {
                Text(text = "Next")
            }
        }
    }

    override fun drawCoachShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        drawScope.apply {
            drawCircle(
                color = backGroundColor,
                radius = radius.value * 4f,
                center = targetBounds.center,
                alpha = backgroundAlpha
            )
        }
        return Rect(
            offset = Offset.Zero - targetBounds.topLeft, size =
            Size(
                width = drawScope.size.width,
                height = targetBounds.maxDimension.absoluteValue * 3f
            )
        )
    }

    override suspend fun enterAnimation(targetBounds: Rect) {
        radius.snapTo(0f)
        radius.animateTo(
            (targetBounds.maxDimension / 2f) + 40f,
            tween(500, easing = FastOutSlowInEasing)
        )

        outerRadius.snapTo(targetBounds.maxDimension)
        outerRadius.animateTo(targetBounds.maxDimension * 2f, animationSpec = animationSpec)
        outerAlphaAnim.animateTo(targetValue = 0f, animationSpec = animationSpec)
    }

    override fun drawTargetShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        drawScope.apply {
            drawCircle(
                color = Color.White,
                radius = radius.value,
                center = targetBounds.center,
                blendMode = BlendMode.Xor
            )
            drawCircle(
                color = backGroundColor,
                center = targetBounds.center,
                radius = outerRadius.value,
                alpha = outerAlphaAnim.value
            )
        }
        return Rect(
            offset = Offset(targetBounds.width * 2f, targetBounds.height * 2f),
            size = targetBounds.size * 2f
        )

    }

    override suspend fun exitAnimation(targetBounds: Rect){
        radius.animateTo(0f, tween(500, easing = FastOutSlowInEasing))
    }
}