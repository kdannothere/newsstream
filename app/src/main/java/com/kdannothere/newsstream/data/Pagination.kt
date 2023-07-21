package com.kdannothere.newsstream.data

data class Pagination(
    val limit: Int = 0,
    val offset: Int = 0,
    val count: Int = 0,
    val total: Int = 0,
)