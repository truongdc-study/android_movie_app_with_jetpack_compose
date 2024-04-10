package com.truongdc.android.base.ui.components.introduce

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

class DefaultCoachStyle : CoachStyle {
    override val backGroundColor: Color
        get() = Color.Black
    override val backgroundAlpha: Float
        get() = 0.8f


    @SuppressLint("ComposableNaming")
    @Composable
    override fun drawCoachButtons(
        contentScope: BoxWithConstraintsScope,
        targetBounds: Rect,
        onBack: () -> Unit,
        onSkip: () -> Unit,
        onNext: () -> Unit
    ) {
        contentScope.apply {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = "X",
                    fontSize = 18.sp,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "スクロールしながら\n" +
                            "今日の平均やアプリの使い方を\n" +
                            "確認してみましょう",
                    fontSize = 18.sp,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "ボタンを押して今日の状態を\n" +
                            "測ってみましょう",
                    fontSize = 18.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 150.dp)
                )
            }
        }
    }

    override fun drawCoachShape(targetBounds: Rect, drawScope: DrawScope): Rect {
        drawScope.apply {
            drawRect(color = backGroundColor.copy(alpha = backgroundAlpha))
        }
        return Rect(Offset.Zero, size = drawScope.size)
    }

}

class CanopasStyle : CoachStyle {
    override val backGroundColor: Color
        get() = Color.Blue
    override val backgroundAlpha: Float
        get() = 0.8f
    private val radius :Animatable<Float,AnimationVector1D> = Animatable(0f)

    @SuppressLint("ComposableNaming")
    @Composable
    override fun drawCoachButtons(
        contentScope: BoxWithConstraintsScope,
        targetBounds: Rect,
        onBack: () -> Unit,
        onSkip: () -> Unit,
        onNext: () -> Unit
    ) {
        LaunchedEffect(key1 = targetBounds, block = {
            radius.animateTo(targetBounds.maxDimension.absoluteValue*3f,
            animationSpec = tween(2000, easing = FastOutSlowInEasing)
            )
        })
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
                radius = radius.value,
                center = targetBounds.center,
                alpha = backgroundAlpha
            )
        }
        return Rect(
            offset = Offset.Zero, size =
            Size(
                width = drawScope.size.width,
                height = targetBounds.maxDimension.absoluteValue * 3f
            )
        )
    }

}