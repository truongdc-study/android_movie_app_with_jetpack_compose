package com.truongdc.android.base.navigation

import androidx.navigation.NamedNavArgument

sealed class AppDestination(
    val route: String = ""
) {
    companion object {
        const val KEY = ""
    }

    open val arguments: List<NamedNavArgument> = emptyList()

    open var destination: String = route

    open var parcelableArgument: Pair<String, Any?> = "" to null

    data class DESTINATION(val router: String) : AppDestination(router)

    data object Up : AppDestination()
    data object Splash : AppDestination("Splash")


}