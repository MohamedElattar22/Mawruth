package com.graduation.data.api

import android.content.SharedPreferences
import com.google.gson.Gson
import com.graduation.domain.model.userlogin.UserLoginDto
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val result = sharedPreferences.getString("userData", null)
        val newRequestBuilder = chain.request().newBuilder()
        result?.let {
            val user = Gson().fromJson(result, UserLoginDto::class.java)
            val token = user.token
            token?.let {
                newRequestBuilder.addHeader("Authorization", token)
            }
        }
        return chain.proceed(newRequestBuilder.build())
    }
}