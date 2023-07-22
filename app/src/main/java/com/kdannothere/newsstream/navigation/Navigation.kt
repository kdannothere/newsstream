package com.kdannothere.newsstream.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.screen.ScreenNewsDetails
import com.kdannothere.newsstream.screen.ScreenSettings
import com.kdannothere.newsstream.screen.ScreenSharing
import com.kdannothere.newsstream.screen.ScreenNewsList

@Composable
fun Navigation(
    navController: NavHostController,
) {
    val viewModel: NewsViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, Routes.screenNewsList) {
            composable(route = Routes.screenNewsList) {
                ScreenNewsList(navController, viewModel)
            }
            composable(route = Routes.screenNewsDetails) {
                ScreenNewsDetails(navController, viewModel)
            }
            composable(route = Routes.screenSharing) {
                ScreenSharing(navController, viewModel)
            }
            composable(route = Routes.screenSettings) {
                ScreenSettings(navController, viewModel)
            }
        }
    }
}