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
import com.truongdc.android.base.features.HomeScreen
import com.truongdc.android.base.features.ProfileScreen
import com.truongdc.android.base.features.SearchScreen
import com.truongdc.android.base.features.SettingScreen
import com.truongdc.android.base.features.dashboard.DashBoardScreen
import com.truongdc.android.base.features.ProfileDetailScreen
import com.truongdc.android.base.features.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavGraphDestination.Splash.destination
    ) {
        composable(destination = NavGraphDestination.Splash) {
            SplashScreen(navController = navController)
        }
        composable(destination = NavGraphDestination.DashBoard) {
            DashBoardScreen(navController = navController)
        }
        composable(destination = NavGraphDestination.ProfileDetail) { backStackEntry ->
            ProfileDetailScreen(
                navHostController = navController,
                name = NavGraphDestination.ProfileDetail.getFullName(backStackEntry),
                age = NavGraphDestination.ProfileDetail.getAge(backStackEntry),
                sex = NavGraphDestination.ProfileDetail.getSex(backStackEntry)
            )
        }
    }
}

@Composable
fun BottomNavHost(
    navDashBoardController: NavHostController = rememberNavController(),
    navMainController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navDashBoardController,
        startDestination = NavGraphDestination.Home.destination
    ) {
        composable(destination = NavGraphDestination.Home) {
            HomeScreen(navController = navMainController)
        }
        composable(destination = NavGraphDestination.Search) {
            SearchScreen(navController = navMainController)
        }
        composable(destination = NavGraphDestination.Profile) {
            ProfileScreen(navController = navMainController)
        }
        composable(destination = NavGraphDestination.Setting) {
            SettingScreen(navController = navMainController)
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

fun <T> NavHostController.previousBackStackWithResult(key: String, value: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
    popBackStack()
}

fun <T> NavBackStackEntry.getResultFromBackStack(key: String): T? {
    return savedStateHandle.get<T>(key)
}

