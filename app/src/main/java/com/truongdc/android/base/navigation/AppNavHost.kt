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
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.features.FirstScreen
import com.truongdc.android.base.features.FourthScreen
import com.truongdc.android.base.features.SecondScreen
import com.truongdc.android.base.features.ThirdScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavGraphDestination.First.destination
    ) {
        composable(destination = NavGraphDestination.First) {
            FirstScreen(navController = navController)
        }
        composable(destination = NavGraphDestination.Seconds) {
            SecondScreen(navController = navController)
        }
        composable(destination = NavGraphDestination.Third) { backStackEntry ->
            NavGraphDestination.Third.getId(backStackEntry)?.let {id ->
                ThirdScreen(navController = navController, id = id)
            }
        }
        composable(destination = NavGraphDestination.Fourth) {
            FourthScreen(navController = navController)
        }
    }
}

private fun NavGraphBuilder.composable(
    destination: NavGraphDestination,
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
    destination: NavGraphDestination,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    when (destination) {
        is NavGraphDestination.Up -> navigateUp()
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
