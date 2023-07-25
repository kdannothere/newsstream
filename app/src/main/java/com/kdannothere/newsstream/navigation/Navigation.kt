package com.kdannothere.newsstream.navigation

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.data.DataManager
import com.kdannothere.newsstream.screen.ScreenNewsDetails
import com.kdannothere.newsstream.screen.ScreenNewsList
import com.kdannothere.newsstream.screen.ScreenSettings
import com.kdannothere.newsstream.screen.SidePanel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// make dark theme setting to be used, make its saving and loading

@Composable
fun Navigation(
    navController: NavHostController,
) {
    val viewModel: NewsViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.screenTitle.value) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            when (drawerState.isClosed) {
                                true -> drawerState.open()
                                false -> drawerState.close()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    if (viewModel.currentRoute.value == Routes.screenNewsDetails) {
                        IconButton(
                            onClick = {
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, viewModel.currentNewsUrl.value)
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->

        ModalDrawer(
            drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            drawerState = drawerState,
            drawerContent = {
                SidePanel(navController, viewModel, drawerState)
            },
            gesturesEnabled = false,
        ) {
            NavHost(
                navController, Routes.screenNewsList,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = Routes.screenNewsList) {
                    viewModel.screenTitle.value = "News"
                    viewModel.currentRoute.value = Routes.screenNewsList
                    ScreenNewsList(navController, viewModel)
                }
                composable(route = Routes.screenNewsDetails) {
                    viewModel.screenTitle.value = "News details"
                    viewModel.currentRoute.value = Routes.screenNewsDetails
                    ScreenNewsDetails(viewModel)
                }
                composable(route = Routes.screenSettings) {
                    viewModel.screenTitle.value = "Settings"
                    viewModel.currentRoute.value = Routes.screenSettings
                    ScreenSettings(navController, viewModel)
                }
            }
        }
    }
}
