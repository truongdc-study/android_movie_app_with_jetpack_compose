package com.truongdc.android.base.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.navigation.NavGraphDestination
import com.truongdc.android.base.navigation.navigate

@Composable
fun ProfileDetailScreen(
    navHostController: NavHostController,
    name: String,
    age: Int,
    sex: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp)
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = "Profile Detail Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(50.dp))
        Row {
            Text(
                text = "Full name:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = name,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Text(
                text = "Age:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = age.toString(),
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Text(
                text = "Sex:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            val sexString = if (sex) {
                "Male"
            } else {
                "FeMale"
            }
            Text(
                text = sexString,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            navHostController.popBackStack()
        }) {
            Text(text = "Back to Profile")
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun PreviewProfileDetail() {
    ProfileDetailScreen(
        navHostController = rememberNavController(),
        name = "truongdc@21",
        age = 25,
        sex = true
    )
}