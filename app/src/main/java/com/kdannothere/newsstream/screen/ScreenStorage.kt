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
fun ScreenStorage(
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
private fun PreviewScreenStorage() {
    ScreenStorage(navController = NavHostController(LocalContext.current))
}

/*
@Composable
fun FileApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("File Manager") }
            )
        }
    ) {
        NavHost(navController, startDestination = "main") {
            composable("main") { MainScreen(navController) }
            composable("settings") { SettingsScreen(navController) }
            composable("share") { ShareScreen(navController) }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Column {
        Text("Main Screen")
        Button(onClick = { navController.navigate("settings") }) {
            Text("Go to Settings")
        }
        Button(onClick = { navController.navigate("share") }) {
            Text("Go to Share")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    var theme by remember { mutableStateOf("Light") }
    Column {
        Text("Settings Screen")
        Text("Current theme: $theme")
        Button(onClick = { theme = if (theme == "Light") "Dark" else "Light" }) {
            Text("Toggle Theme")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun ShareScreen(navController: NavHostController) {
    Column {
        Text("Share Screen")
        // Use ContentProvider and ContentResolver here to share files with other apps
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FileApp()
}
 */