package com.kdannothere.newsstream.screen

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.kdannothere.newsstream.NewsViewModel
import com.kdannothere.newsstream.data.NewsArticle
import com.kdannothere.newsstream.navigation.Routes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenNewsList(
    navController: NavHostController,
    viewModel: NewsViewModel,
) {
    val newsArticles by viewModel.newsArticles.collectAsState()
    val error by viewModel.error.collectAsState()

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, onRefresh = {
            viewModel.fetchNews()
        })

    Column(modifier = Modifier.pullRefresh(pullRefreshState)) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .zIndex(1f)
        )
        when {
            (error != null) -> {
                Text(text = "Error: $error")
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(newsArticles) { article ->
                        NewsCard(navController, viewModel, article)
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsCard(
    navController: NavHostController,
    viewModel: NewsViewModel,
    article: NewsArticle,
) {
    val context = LocalContext.current
    Card(
        backgroundColor = Color.DarkGray,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .combinedClickable(onClick = {
                viewModel.currentNewsUrl.value = article.url.toString()
                navController.navigate(Routes.screenNewsDetails)
            },
                onLongClick = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, viewModel.currentNewsUrl.value)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                })
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