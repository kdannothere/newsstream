package com.kdannothere.newsstream

import androidx.compose.material.DrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdannothere.newsstream.data.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository = NewsRepository(),
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    val newsArticles = newsRepository.newsArticles
    val pagination = newsRepository.pagination
    val error = newsRepository.error
    var currentNewsUrl = mutableStateOf("null")
    var screenTitle = mutableStateOf("News")
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch(dispatcherIO) {
            changeRefreshState(true)
            newsRepository.fetchNews()
            changeRefreshState(false)
        }
    }

    private fun changeRefreshState(value: Boolean) {
        viewModelScope.launch {
            _isRefreshing.emit(value)
        }
    }

    fun closeDrawer(drawerState: DrawerState, scope: CoroutineScope) {
        scope.launch {
            if (drawerState.isOpen) drawerState.close()
        }
    }
}