package com.kdannothere.newsstream.data

import retrofit2.http.GET
import retrofit2.http.Query

fun interface NewsApi {

    @GET("news")
    suspend fun getNews(@Query("access_key") apiKey: String): NewsResponse
}