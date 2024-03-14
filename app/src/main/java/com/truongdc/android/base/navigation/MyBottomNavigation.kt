package com.truongdc.android.base.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.truongdc.android.base.features.components.BottomNavItem

@Composable
fun MyBottomNavigation(navController: NavHostController) {
    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val listBottomNavItem = mutableListOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Profile,
            BottomNavItem.Setting,
        )
        listBottomNavItem.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(
                        destination = NavGraphDestination.DESTINATION(
                            item.route
                        )
                    ) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}