package com.kdannothere.newsstream

import android.app.Application
import android.webkit.WebViewClient
import androidx.compose.runtime.mutableStateOf
import com.kdannothere.newsstream.data.DataManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class NewsApp: Application() {

    companion object {
        val webViewClient = WebViewClient()
        val darkTheme = mutableStateOf<Boolean?>(null)
    }

    init {
        GlobalScope.launch {
            val savedThemeSetting =
                async(Dispatchers.IO) {
                    DataManager.loadThemeSetting(applicationContext)
                }
            if (savedThemeSetting.await() != null) {
                darkTheme.value = savedThemeSetting.await()
            }
        }
    }
}