package com.truongdc.android.base.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.truongdc.android.base.extensions.defaultEmpty
import com.truongdc.android.base.extensions.defaultTrue
import com.truongdc.android.base.extensions.defaultZero

sealed class NavGraphDestination(
    val route: String = ""
) {
    companion object {
        const val KEY_FULL_NAME = "key_full_name"
        const val KEY_AGE = "key_age"
        const val KEY_SEX = "key_sex"
    }

    open val arguments: List<NamedNavArgument> = emptyList()

    open var destination: String = route

    open var parcelableArgument: Pair<String, Any?> = "" to null

    data class DESTINATION(val router: String) : NavGraphDestination(router)

    data object Up : NavGraphDestination()

    data object Splash : NavGraphDestination("splash")

    data object DashBoard : NavGraphDestination("dashboard")

    data object Home : NavGraphDestination("home")

    data object Search : NavGraphDestination("search")

    data object Profile : NavGraphDestination("profile")

    data object Setting : NavGraphDestination("setting")


    data object ProfileDetail :
        NavGraphDestination("profile_detail/{$KEY_FULL_NAME}/{$KEY_AGE}/{$KEY_SEX}") {
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(KEY_FULL_NAME) { type = NavType.StringType },
                navArgument(KEY_AGE) { type = NavType.IntType },
                navArgument(KEY_SEX) { type = NavType.BoolType }
            )

        fun getFullName(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getString(KEY_FULL_NAME).defaultEmpty()

        fun getAge(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getInt(KEY_AGE).defaultZero()

        fun getSex(backStackEntry: NavBackStackEntry) =
            backStackEntry.arguments?.getBoolean(KEY_SEX).defaultTrue()

        fun createRoute(fullName: String, age: Int, sex: Boolean) = apply{
            destination = "profile_detail/$fullName/$age/$sex"
        }
    }


}