package com.kdannothere.newsstream.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kdannothere.newsstream.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenSharing(
    navController: NavHostController,
    viewModel: NewsViewModel,
) {
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
    }
}