package com.kdannothere.newsstream.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kdannothere.newsstream.NewsViewModel

@Composable
fun ScreenSettings(
    navController: NavHostController,
) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val viewModel = backStackEntry?.let { entry ->
        viewModel<NewsViewModel>(entry)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
    }
}

@Preview
@Composable
private fun PreviewScreenSettings() {
    ScreenSettings(navController = NavHostController(LocalContext.current))
}