package com.truongdc.android.base.ui.components.introduce.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.LayoutCoordinates
import com.truongdc.android.base.ui.components.introduce.CoachStyle
import com.truongdc.android.base.ui.components.introduce.DefaultCoachStyle
import com.truongdc.android.base.ui.components.introduce.RectangleRevealEffect
import com.truongdc.android.base.ui.components.introduce.RevealEffect
data class CoachData(
    val coordinates: LayoutCoordinates,
    val content : @Composable (BoxWithConstraintsScope.() -> Unit),
    val revealEffect: RevealEffect = RectangleRevealEffect(),
    val alignment: Alignment = Alignment.BottomCenter,
    val isForcedAlignment : Boolean = false,
    val coachStyle: CoachStyle = DefaultCoachStyle()
)


