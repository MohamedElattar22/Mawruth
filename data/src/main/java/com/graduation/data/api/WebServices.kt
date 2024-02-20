package com.graduation.data.api


import com.graduation.data.model.categories.CategoriesResponseItem
import com.graduation.data.model.museum.MuseumResponseItem
import com.graduation.data.model.userinfo.UserInformation
import com.graduation.data.model.userlogin.UserLoginResponse
import com.graduation.data.model.usersignup.OTPResponse
import com.graduation.data.model.usersignup.SignupResponse
import com.graduation.domain.model.ResendOtpData
import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.model.signupdata.SignUpRequiredData
import com.graduation.domain.model.userlogin.UserLoginPost
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


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

    @POST("users/otp/resend")
    suspend fun resendOTP(
        @Body email: ResendOtpData
    ): OTPResponse


    @GET("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Int
    ): UserInformation

    @GET("users/email/{email}")
    suspend fun getUserInfoByEmail(
        @Path("email") userEmail: String
    ): UserInformation

    @Multipart
    @PATCH("users/email/{email}")
    suspend fun updateUserData(
        @Path("email")
        emailPath: String,
        @Part("full_name") fullName: RequestBody? = null,
        @Part("username") username: RequestBody? = null,
        @Part("email") email: RequestBody? = null,
        @Part("password") password: RequestBody? = null,
        @Part("phone_number") phoneNumber: RequestBody? = null,
        @Part avatar: MultipartBody.Part? = null
    ): UserInformation

    @GET("types")
    suspend fun getCategories(
    ): List<CategoriesResponseItem?>?
}