package com.truongdc.android.base.ui.components.introduce

import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import com.truongdc.android.base.ui.components.introduce.model.CoachData


internal fun Map<Int, CoachData>.hasNextValue(dropCount : Int)= values.drop(dropCount.coerceAtLeast(0)).iterator().hasNext()
internal fun Map<Int, CoachData>.nextKey(dropCount: Int) = keys.drop(dropCount.coerceAtLeast(0)).iterator().next()
internal fun Map<Int, CoachData>.firstKey(dropCount : Int) = keys.drop(dropCount.coerceAtLeast(0)).first()
internal fun Map<Int, CoachData>.nextValue(dropCount: Int) = values.drop(dropCount.coerceAtLeast(0)).iterator().next()
/**
 *a position based object should be converted to
 * AnimationVector2D, whereas an object that describes
 * rectangle bounds should convert to AnimationVector4D.
 */
internal val rectToVector = TwoWayConverter(
    convertToVector = { rect: Rect ->
        AnimationVector4D(rect.left, rect.top, rect.width, rect.height)
    },
    convertFromVector = { vector: AnimationVector4D ->
        Rect(
            offset = Offset(vector.v1, vector.v2),
            size = Size(vector.v3, vector.v4)
        )
    }
)
fun Modifier.addTarget(
    position : Int,
    state : CoachMarkState,
    content : @Composable BoxWithConstraintsScope.() -> Unit,
    revealEffect: RevealEffect = RectangleRevealEffect(),
    backgroundCoachStyle: CoachStyle = DefaultCoachStyle(),
    alignment: Alignment = Alignment.BottomCenter,
    isForcedAlignment : Boolean = false,
    ) = onGloballyPositioned {
    layoutCoordinates ->
    state.targetList[position] = CoachData(
        content= content,
        coordinates = layoutCoordinates,
        revealEffect = revealEffect,
        alignment = alignment,
        isForcedAlignment = isForcedAlignment,
        coachStyle = backgroundCoachStyle
    )
}
fun Modifier.addTargetOnPlaced(
    position : Int,
    state : CoachMarkState,
    content : @Composable BoxWithConstraintsScope.() -> Unit,
    revealEffect: RevealEffect = RectangleRevealEffect(),
    backgroundCoachStyle: CoachStyle = DefaultCoachStyle(),
    alignment: Alignment = Alignment.BottomCenter,
    isForcedAlignment : Boolean = false,
) = onPlaced {
        layoutCoordinates ->
    state.targetList[position] = CoachData(
        content= content,
        coordinates = layoutCoordinates,
        revealEffect = revealEffect,
        alignment = alignment,
        isForcedAlignment = isForcedAlignment,
        coachStyle = backgroundCoachStyle
    )
}

class CoachMarkState{
    internal val targetList = mutableStateMapOf<Int, CoachData>()
}

@Composable
fun rememberCoachMarkState() : CoachMarkState {
    return remember {
        CoachMarkState()
    }
}

fun Color.invert(): Color {
    val red = 1f - this.red
    val green = 1f - this.green
    val blue = 1f - this.blue
    return Color(red, green, blue,this.alpha,this.colorSpace)
}
