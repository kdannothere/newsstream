package com.kdannothere.newsstream.screen

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.kdannothere.newsstream.NewsApp
import com.kdannothere.newsstream.NewsViewModel

@Composable
fun ScreenNewsDetails(
    navController: NavHostController,
    viewModel: NewsViewModel,
) {
    if (viewModel.currentNewsUrl.value == "null") return
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = NewsApp.webViewClient
                loadUrl(viewModel.currentNewsUrl.value)
            }
        }
    )
}