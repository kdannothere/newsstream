package com.kdannothere.newsstream.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

fun interface NewsApi {

    @GET("news")
    suspend fun getNews(@Header("access_key") apiKey: String): Response<ApiResponse>
}