package com.graduation.data.model.api

import com.graduation.data.model.signup.SignupResponse
import com.graduation.domain.model.SignUpRequiredData
import retrofit2.http.Body
import retrofit2.http.POST


interface WebServices {

    @POST("users/register")
    suspend fun signUpUser(
        @Body userData: SignUpRequiredData
    ): SignupResponse


}