package com.truongdc.android.base.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.navigation.NavGraphDestination
import com.truongdc.android.base.navigation.navigate

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(text = "Profile Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(24.dp))
        Button(onClick = {
            navController.navigate(
                NavGraphDestination.ProfileDetail.createRoute(
                    fullName = "Đàng Chí Trường",
                    age = 25,
                    sex = true
                )
            ) {
                popUpTo(NavGraphDestination.DashBoard.route) {
                    inclusive = false
                }
            }
        }) {
            Text(text = "See Profile Detail")
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}