package com.truongdc.android.base.ui.components.introduce

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import kotlinx.coroutines.launch
import androidx.compose.material3.Surface

@Composable
internal fun Coach(
    modifier: Modifier = Modifier,
    coordinates: LayoutCoordinates,
    content: @Composable BoxWithConstraintsScope.() -> Unit,
    coachStyle: CoachStyle = DefaultCoachStyle(),
    revealEffect: RevealEffect = RectangleRevealEffect(),
    alignment: Alignment = Alignment.BottomCenter,
    isForcedAlignment: Boolean = false,
    onBack: () -> Unit = {},
    onSkip: () -> Unit = {},
    onNext: () -> Unit,
) {
    val bounds = coordinates.boundsInRoot()
    LaunchedEffect(key1 = bounds) {
       revealEffect.enterAnimation(bounds)
    }
    val scope = rememberCoroutineScope()
    var newBound by remember {
        mutableStateOf(Rect(Offset.Zero, Size.Zero))
    }
    var newSize by remember {
        mutableStateOf(Rect(Offset.Zero, Size.Zero))
    }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(alpha = 0.99f)
            .pointerInput(bounds) {
                detectTapGestures {
                    scope.launch {
                        revealEffect.exitAnimation(bounds)
                        onNext()
                    }
                }
            },
        color = Color.Transparent
    )
    {
        Canvas(modifier = Modifier, onDraw = {
            newSize = coachStyle.drawCoachShape(bounds, this@Canvas)
            newBound = revealEffect.drawTargetShape(bounds, this@Canvas)
        })
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
          coachStyle.drawCoachButtons(
                contentScope = this,
                onSkip = {
                    scope.launch {
                        revealEffect.exitAnimation(bounds)
                        onSkip()
                    }
                },
                onNext = {
                    scope.launch {
                        revealEffect.exitAnimation(bounds)
                        onNext()
                    }
                },
                onBack = {
                    scope.launch {
                        revealEffect.exitAnimation(bounds)
                        onBack()
                    }

                },
                targetBounds = bounds
            )
                NewCoachLayout(
                    canvasRect = newSize,
                    targetBound = newBound,
                    alignment= alignment,
                    isForcedAlignment= isForcedAlignment
                ) {
                        content()
                }
        }
    }
}

