package com.truongdc.android.base.ui.components.introduce

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun CoachMark(
    modifier: Modifier = Modifier,
    coachMarkState: CoachMarkState,
    showCoachMark: Boolean = true,
    onShowBegin: () -> Unit = {},
    onBeforeShowingCoachMark: (Int, Int) -> Unit = { _, _ -> },
    onAfterShowingCoachMark : (Int, Int) -> Unit = { _ , _-> },
    onBack : () -> Unit = {},
    onCancelled: () -> Unit,
    onCompleted: () -> Unit,
) {
    var canDrawCoachMark by rememberSaveable {
        mutableStateOf(showCoachMark)
    }
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    var canShowNext by rememberSaveable(coachMarkState.targetList.size ) {
        mutableStateOf(coachMarkState.targetList.hasNextValue(count) && canDrawCoachMark)
    }
    val onShowStart by rememberUpdatedState(onShowBegin)

    if (canShowNext) {
        if (coachMarkState.targetList.isNotEmpty()) {
            //even after the recomposition this shouldn't fire again
            LaunchedEffect(key1 = Unit) {
                onShowStart()
            }
            LaunchedEffect(key1 = coachMarkState.targetList.nextKey(count), block = {
                onBeforeShowingCoachMark(count,coachMarkState.targetList.firstKey(count))
            })
            val currentTarget =  coachMarkState.targetList.nextValue(count)
            Coach(
                content = currentTarget.content,
                coordinates = currentTarget.coordinates,
                onBack = {
                    canShowNext = if(count == 0) false
                    else {
                        onBack()
                        --count
                        coachMarkState.targetList.hasNextValue(count)

                    }
                },
                onSkip = {
                    canDrawCoachMark = false
                    canShowNext = false
                    onCancelled()
                },
                onNext = {
                    onAfterShowingCoachMark(count,coachMarkState.targetList.firstKey(count))
                    ++count
                    canShowNext = coachMarkState.targetList.hasNextValue(count)
                    //reset count and mark finish
                    if (!canShowNext) {
                        count = 0
                        onCompleted()
                        canDrawCoachMark = false
                    }
                },
                isForcedAlignment = currentTarget.isForcedAlignment,
                alignment = currentTarget.alignment,
                modifier = modifier,
                revealEffect = currentTarget.revealEffect,
                coachStyle = currentTarget.coachStyle
            )
        }
    }
}