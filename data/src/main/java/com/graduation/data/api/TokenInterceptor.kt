package com.graduation.data.api

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val result = sharedPreferences.getString("token", null)
        val newRequestBuilder = chain.request().newBuilder()
        result?.let {
            newRequestBuilder.addHeader("Authorization", it)
        }
        return chain.proceed(newRequestBuilder.build())
    }
}