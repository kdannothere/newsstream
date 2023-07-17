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
import com.kdannothere.newsstream.screen.ScreenSettings
import com.kdannothere.newsstream.screen.ScreenSharing
import com.kdannothere.newsstream.screen.ScreenStorage

@Composable
fun Navigation(
    navController: NavHostController,
) {
    val viewModel: NewsViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, Routes.screenStorage) {
            composable(route = Routes.screenStorage) {
                ScreenStorage(navController)
            }
            composable(route = Routes.screenSharing) {
                ScreenSharing(navController)
            }
            composable(route = Routes.screenSettings) {
                ScreenSettings(navController)
            }
        }
    }
}