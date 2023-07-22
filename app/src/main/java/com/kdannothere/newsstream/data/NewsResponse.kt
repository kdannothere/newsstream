package com.kdannothere.newsstream.data

data class NewsResponse(
    val pagination: Pagination,
    val data: List<NewsArticle>
)