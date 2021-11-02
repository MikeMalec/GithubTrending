package com.example.githubtrending.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkLogger : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.d("HTTP", "HTTP_${request.url()}")
        Log.d("HTTP", "HTTP_CODE = ${response.code()}")
        Log.d("HTTP", "HTTP_MESSAGE = ${response.message()}")
        Log.d("HTTP", "HTTP_BODY = ${response.body().toString()}")
        return response
    }
}