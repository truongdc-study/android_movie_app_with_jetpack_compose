package com.truongdc.android.base.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostController.AppNavHost() {
    NavHost(navController = this, startDestination = AppDestination.Up.destination) {
        composable(AppDestination.Up) {

        }
    }
}

private fun NavGraphBuilder.composable(
    destination: AppDestination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = deepLinks,
        content = content
    )
}

fun NavHostController.navigate(
    destination: AppDestination,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    when (destination) {
        is AppDestination.Up -> navigateUp()
        else -> {
            destination.parcelableArgument.let { (key, value) ->
                currentBackStackEntry?.savedStateHandle?.set(key, value)
            }
            navigate(
                route = destination.destination,
                builder = builder,
            )
        }
    }
}
