package com.graduation.data.api

import com.graduation.data.model.museum.MuseumResponseItem
import com.graduation.data.model.userinfo.UserInformation
import com.graduation.data.model.userlogin.UserLoginResponse
import com.graduation.data.model.usersignup.OTPResponse
import com.graduation.data.model.usersignup.SignupResponse
import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.model.signupdata.SignUpRequiredData
import com.graduation.domain.model.userlogin.UserLoginPost
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


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

    @GET("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Int
    ): UserInformation

    @Multipart
    @PATCH("users/")
    suspend fun updateUserData(
        @Query("userId")
        userId: String,
        @Part("full_name") fullName: MultipartBody,
        @Part("username") userName: MultipartBody,
        @Part("email") email: MultipartBody,
        @Part("password") password: MultipartBody,
        @Part("phone_number") phoneNumber: MultipartBody,
        @Part("avatar") avatar: MultipartBody.Part
    )
}