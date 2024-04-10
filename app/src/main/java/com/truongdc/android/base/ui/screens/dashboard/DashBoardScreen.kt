package com.truongdc.android.base.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.truongdc.android.base.R
import com.truongdc.android.base.ui.components.introduce.CoachMark
import com.truongdc.android.base.ui.screens.movie.MovieListScreen
import com.truongdc.android.base.ui.theme.AppColors
import com.truongdc.android.base.ui.theme.DpSize
import com.truongdc.android.base.ui.theme.SpSize
import com.truongdc.android.base.ui.components.introduce.CircleRevealEffect
import com.truongdc.android.base.ui.components.introduce.CoachMarkState
import com.truongdc.android.base.ui.components.introduce.DefaultCoachStyle
import com.truongdc.android.base.ui.components.introduce.addTarget
import com.truongdc.android.base.ui.components.introduce.rememberCoachMarkState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: DashBoardViewModel = hiltViewModel()
) {
    val coachMarkState = rememberCoachMarkState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_movie),
                        colorFilter = ColorFilter.tint(color = AppColors.Black),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = DpSize.dp8, start = DpSize.dp16)
                            .width(DpSize.dp30)
                            .height(DpSize.dp30)
                    )
                    androidx.compose.material3.Text(
                        text = "MOVIE APP",
                        fontSize = SpSize.sp18,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_logout),
                        colorFilter = ColorFilter.tint(color = AppColors.Indigo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = DpSize.dp24)
                            .clickable {
                                // viewModel.onHandleLogOut()
                            }
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(AppColors.Yellow))
        },
        bottomBar = {
            MyBottomBar(coachMarkState)
        },
        content = {
            MovieListScreen(navHostController, paddingValues = it)
        }
    )

    CoachMark(
        coachMarkState = coachMarkState,
        showCoachMark = true,
        onCancelled = {},
        onCompleted = {}
    )
}

@Composable
private fun MyBottomBar(coachMarkState: CoachMarkState) {
    Box {
        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomNavigationItem(
                selected = true,
                onClick = {
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = { Text("Home") }
            )

            BottomNavigationItem(
                selected = false,
                onClick = {
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                },
                label = { Text("Favorite") }
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -DpSize.dp25)
                .addTarget(1, state = coachMarkState,
                    revealEffect = CircleRevealEffect(),
                    backgroundCoachStyle = DefaultCoachStyle(),
                    content = {

                    }),
            shape = CircleShape,
            onClick = {

            }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Start",
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)

            )
        }
    }

}