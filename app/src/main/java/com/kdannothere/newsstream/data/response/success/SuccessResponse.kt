package com.kdannothere.newsstream.data.response.success

import com.kdannothere.newsstream.data.NewsArticle
import com.kdannothere.newsstream.data.Pagination

data class SuccessResponse(
    val pagination: Pagination,
    val data: List<NewsArticle>
)