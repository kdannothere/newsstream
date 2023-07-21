package com.kdannothere.newsstream.data

data class NewsArticle(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String,
    val category: String,
    val language: String,
    val country: String,
    val published_at: String
)