package com.kdannothere.newsstream

import android.app.Application
import android.webkit.WebViewClient

class NewsApp: Application() {

    companion object {
        val webViewClient = WebViewClient()
    }
}