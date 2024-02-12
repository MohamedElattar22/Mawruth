package com.graduation.data.api

import com.graduation.data.model.museum.MuseumResponseItem
import com.graduation.data.model.userlogin.UserLoginResponse
import com.graduation.data.model.usersignup.OTPResponse
import com.graduation.data.model.usersignup.SignupResponse
import com.graduation.domain.model.EmailConfirmationData
import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.userlogin.UserLoginPost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface WebServices {

    @POST("users/register")
    suspend fun signUpUser(
        @Body userData: SignUpRequiredData
    ): SignupResponse

    @GET("museums")
    suspend fun getMuseums(): List<MuseumResponseItem?>?

    @POST("users/login")
    suspend fun loginUser(
        @Body userLoginPost: UserLoginPost
    ): UserLoginResponse

    @POST("users/otp/verify")
    suspend fun verifyOTP(
        @Body confirmationData: EmailConfirmationData
    ): OTPResponse

}