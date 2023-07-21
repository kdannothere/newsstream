package com.kdannothere.newsstream.data

import android.util.Log
import com.kdannothere.newsstream.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "http://api.mediastack.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



class NewsRepository(
    private val newsApi: NewsApi = retrofit.create(NewsApi::class.java),
    private val apiKey: String = BuildConfig.API_KEY,
) {

    private val _newsArticles = MutableStateFlow<List<NewsArticle>>(emptyList())
    val newsArticles = _newsArticles.asStateFlow()

    private val _pagination = MutableStateFlow(Pagination())
    val pagination = _pagination.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    suspend fun fetchNews() {
        try {
            val response = newsApi.getNews(apiKey)
            val responseBody = response.body()
            if (response.isSuccessful) {
                // Handle successful response
                if (responseBody != null) {
                    _pagination.emit(responseBody.successResponse?.pagination ?: Pagination())
                    _newsArticles.emit(responseBody.successResponse?.data ?: emptyList())
                } else {
                    _pagination.emit(Pagination())
                    _newsArticles.emit(emptyList())
                }
                _error.emit(null)
            } else {
                // Handle error response
                _error.emit(responseBody?.errorResponse?.error?.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            _error.emit(e.localizedMessage)
            Log.d("myLog", "Exception")
        }
    }
}
