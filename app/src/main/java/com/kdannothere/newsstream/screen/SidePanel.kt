package com.kdannothere.newsstream.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DrawerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.R
import com.kdannothere.newsstream.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun SidePanel(
    navController: NavHostController,
    viewModel: NewsViewModel,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackHandler(
            enabled = true,
            onBack = {
                scope.launch {
                    when (drawerState.isOpen) {
                        true -> drawerState.close()
                        false -> {
                            if (navController.previousBackStackEntry == null) {
                                val activity = context as? Activity
                                activity?.finish()
                            } else {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize(0.3f)
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "",
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "News",
                fontSize = 30.sp,
                modifier = Modifier.clickable {
                    viewModel.closeDrawer(drawerState, scope)
                    navController.navigate(Routes.screenNewsList)
                })
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Settings",
                fontSize = 30.sp,
                modifier = Modifier.clickable {
                    viewModel.closeDrawer(drawerState, scope)
                    navController.navigate(Routes.screenSettings)
                })
        }
    }
}