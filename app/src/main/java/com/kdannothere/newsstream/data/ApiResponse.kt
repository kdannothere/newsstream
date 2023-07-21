package com.kdannothere.newsstream.data

import com.kdannothere.newsstream.data.response.error.ErrorResponse
import com.kdannothere.newsstream.data.response.success.SuccessResponse

data class ApiResponse(
    val successResponse: SuccessResponse? = null,
    val errorResponse: ErrorResponse? = null,
)