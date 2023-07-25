package com.kdannothere.newsstream.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kdannothere.newsstream.NewsApp
import com.kdannothere.newsstream.NewsViewModel

@Composable
fun ScreenSettings(
    navController: NavHostController,
    viewModel: NewsViewModel,
) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Box {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark theme",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = if (NewsApp.darkTheme.value == null) {
                        isSystemInDarkTheme()
                    } else {
                        NewsApp.darkTheme.value!!
                    },
                    onCheckedChange = { state ->
                        viewModel.saveThemeSetting(context, state)
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .scale(1.4f)
                )
            }
        }
    }
}