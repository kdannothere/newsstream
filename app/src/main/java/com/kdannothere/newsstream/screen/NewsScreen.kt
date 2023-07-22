package com.kdannothere.newsstream.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.data.NewsArticle

@Composable
fun NewsScreen(
    navController: NavHostController,
    viewModel: NewsViewModel,
) {
    val newsArticles by viewModel.newsArticles.collectAsState()
    val error by viewModel.error.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "News") }
                )
            }
        ) { paddingValues ->
            when {
                (error != null) -> {
                    Text(text = "Error: $error")
                }

                newsArticles.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator(Modifier
                            .fillMaxSize(0.2f)
                            .align(Alignment.Center)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        items(newsArticles) { article ->
                            NewsCard(article)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(article: NewsArticle) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title.toString(), style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.description.toString(), style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            LoadImage(url = article.image.toString())
        }
    }
}

@Composable
fun LoadImage(url: String) {
    if (url == "null") return
    val painter = rememberAsyncImagePainter(url)
    Box {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Fit
        )
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}