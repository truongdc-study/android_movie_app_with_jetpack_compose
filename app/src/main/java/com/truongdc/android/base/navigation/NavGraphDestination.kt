package com.truongdc.android.base.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavGraphDestination(
    val route: String = ""
) {
    companion object {
        const val KEY_ID = "id"
    }

    open val arguments: List<NamedNavArgument> = emptyList()

    open var destination: String = route

    open var parcelableArgument: Pair<String, Any?> = "" to null

    data object Up : NavGraphDestination()

    data object First : NavGraphDestination("first")

    data object Seconds : NavGraphDestination("second")

    data object Third : NavGraphDestination("third/{$KEY_ID}") {

        override val arguments = listOf(
            navArgument(KEY_ID) { type = NavType.StringType }
        )

        fun getId(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getString(KEY_ID)

        fun createRouter(id: String) = apply {
            destination = "third/$id"
        }
    }

    data object Fourth : NavGraphDestination("fourth")

}