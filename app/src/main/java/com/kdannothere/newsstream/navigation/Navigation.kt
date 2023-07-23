package com.kdannothere.newsstream.navigation

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
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.screen.ScreenNewsDetails
import com.kdannothere.newsstream.screen.ScreenSettings
import com.kdannothere.newsstream.screen.ScreenSharing
import com.kdannothere.newsstream.screen.ScreenNewsList
import com.kdannothere.newsstream.screen.SidePanel
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    navController: NavHostController,
) {
    val viewModel: NewsViewModel = viewModel()
    val scope = rememberCoroutineScope()
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
                    ScreenNewsList(navController, viewModel)
                }
                composable(route = Routes.screenNewsDetails) {
                    viewModel.screenTitle.value = "News details"
                    ScreenNewsDetails(viewModel)
                }
                composable(route = Routes.screenSharing) {
                    viewModel.screenTitle.value = "Sharing"
                    ScreenSharing(navController, viewModel)
                }
                composable(route = Routes.screenSettings) {
                    viewModel.screenTitle.value = "Settings"
                    ScreenSettings(navController, viewModel)
                }
            }
        }
    }
}
