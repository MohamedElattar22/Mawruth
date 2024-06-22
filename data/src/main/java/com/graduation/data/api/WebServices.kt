package com.graduation.data.api


import com.graduation.data.model.VerificationResponseDto
import com.graduation.data.model.authuserdata.AuthenticationResponseDto
import com.graduation.data.model.categories.CategoriesResponseDto
import com.graduation.data.model.museums.MuseumItemDto
import com.graduation.data.model.museums.MuseumsResponseDto
import com.graduation.data.model.pieces.PiecesItemDto
import com.graduation.data.model.pieces.PiecesResponseDto
import com.graduation.data.model.reviews.AllReviewsResponseDto
import com.graduation.data.model.reviews.ReviewsResponseDto
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.model.reviews.ReviewsData
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface
WebServices {
    @POST("auth/signup")
    suspend fun signUpUser(
        @Body userData: User
    ): VerificationResponseDto?

    @GET("museums")
    suspend fun getAllMuseums(
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
        @Query("name") name: String? = null,
        @Query("category") category: String? = null,
        @Query("city") city: String? = null,
    ): MuseumsResponseDto?

    @POST("auth/login")
    suspend fun loginUser(
        @Body userLoginPost: User
    ): AuthenticationResponseDto?

    @POST("auth/verify-otp")
    suspend fun verifyOTP(
        @Body confirmationData: User
    ): AuthenticationResponseDto?

    @POST("auth/resend-otp")
    suspend fun resendOTP(
        @Body email: User
    ): VerificationResponseDto?


    @GET("users/me")
    suspend fun getUserInfo(): AuthenticationResponseDto?

    @GET("museums/{museumId}")
    suspend fun getMuseumById(
        @Path("museumId") museumId: Int
    ): MuseumItemDto?

    @GET("pieces/{pieceId}")
    suspend fun getPieceById(
        @Path("pieceId") pieceId: Int
    ): PiecesItemDto?

    @GET("museum/pieces/{museumId}")
    suspend fun getMuseumPieces(
        @Path("museumId") museumId: Int,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
        @Query("name") name: String? = null,
    ): PiecesResponseDto?



    @Multipart
    @PUT("users/upload-image")
    suspend fun updateUserImage(
        @Part avatar: MultipartBody.Part?
    ): AuthenticationResponseDto?

    @PUT("users/me")
    suspend fun updateUserName(
        @Body name: String?
    ): AuthenticationResponseDto?

    @GET("categories")
    suspend fun getCategories(
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
    ): CategoriesResponseDto?

    @POST("museums/{museum_id}/reviews")
    suspend fun sendReview(
        @Path("museum_id") museumId: Int,
        @Body review: ReviewsData
    ): ReviewsResponseDto?


    @GET("museum/{museum_id}/reviews")
    suspend fun getReview(
        @Path("museum_id") museumId: Int,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
    ): AllReviewsResponseDto?
}