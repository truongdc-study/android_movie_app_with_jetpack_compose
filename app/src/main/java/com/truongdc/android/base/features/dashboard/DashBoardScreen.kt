package com.truongdc.android.base.features.dashboard

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.navigation.BottomNavHost
import com.truongdc.android.base.navigation.MyBottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(navController: NavHostController) {
    val navDashBoarController = rememberNavController()
    Scaffold(
        bottomBar = {
            MyBottomNavigation(navController = navDashBoarController)
        }
    ) {
        BottomNavHost(
            navDashBoardController = navDashBoarController,
            navMainController = navController
        )
    }
}
