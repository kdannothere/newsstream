package com.kdannothere.newsstream

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdannothere.newsstream.data.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository = NewsRepository(),
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    ) : ViewModel() {
    val newsArticles = newsRepository.newsArticles
    val pagination = newsRepository.pagination
    val error = newsRepository.error

    init {
        viewModelScope.launch(dispatcherIO) {
            newsRepository.fetchNews()
        }
    }
}