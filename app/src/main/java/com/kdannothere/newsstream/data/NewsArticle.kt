package com.kdannothere.newsstream.data

data class NewsArticle(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val source: String? = null,
    val image: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null,
    val publishedAt: String? = null,
)