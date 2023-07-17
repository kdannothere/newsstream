package com.kdannothere.newsstream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.kdannothere.newsstream.ui.theme.NewsTheme
import com.kdannothere.newsstream.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}