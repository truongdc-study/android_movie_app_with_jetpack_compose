package com.truongdc.android.base.features.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.truongdc.android.base.navigation.NavGraphDestination

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem(NavGraphDestination.Home.route, Icons.Default.Home, "Home")
    data object Search : BottomNavItem(NavGraphDestination.Search.route, Icons.Default.Search, "Search")
    data object Profile : BottomNavItem(NavGraphDestination.Profile.route, Icons.Default.Person, "Profile")
    data object Setting : BottomNavItem(NavGraphDestination.Setting.route, Icons.Default.Settings, "Setting")
}
