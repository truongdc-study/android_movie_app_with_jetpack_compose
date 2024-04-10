package com.truongdc.android.base.ui.components.introduce.model

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.LayoutCoordinates
import com.truongdc.android.base.ui.components.introduce.CoachStyle
import com.truongdc.android.base.ui.components.introduce.CircleRevealEffect
import com.truongdc.android.base.ui.components.introduce.DefaultCoachStyle
import com.truongdc.android.base.ui.components.introduce.RevealEffect


data class Target(
    val coordinates : LayoutCoordinates,
    val revealEffect: RevealEffect = CircleRevealEffect(),
    val coachStyle: CoachStyle = DefaultCoachStyle(),
    val alignment: Alignment = Alignment.BottomCenter,
    val isForcedAlignment : Boolean = false,
    val content : BoxScope.() -> Unit
)