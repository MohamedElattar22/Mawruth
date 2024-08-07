package com.graduation.data.api


import com.graduation.data.model.VerificationResponseDto
import com.graduation.data.model.ai.AIResponseDto
import com.graduation.data.model.authuserdata.AuthenticationResponseDto
import com.graduation.data.model.categories.CategoriesResponseDto
import com.graduation.data.model.collection.CollectionsResponseDto
import com.graduation.data.model.favourite.FavouriteMuseumResponseDto
import com.graduation.data.model.favourite.FavouritePiecesResponseDto
import com.graduation.data.model.favourite.FavouriteTestDto
import com.graduation.data.model.halls.HallsResponseDto
import com.graduation.data.model.museums.MuseumItemDto
import com.graduation.data.model.museums.MuseumsResponseDto
import com.graduation.data.model.pieces.PiecesItemDto
import com.graduation.data.model.pieces.PiecesResponseDto
import com.graduation.data.model.reviews.AllReviewsResponseDto
import com.graduation.data.model.reviews.ReviewsResponseDto
import com.graduation.data.model.stories.StoriesResponceDto
import com.graduation.domain.model.PasswordData
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.model.reviews.ReviewsData
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
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
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("name") name: String? = null,
        @Query("category") category: String? = null,
        @Query("city") city: String? = null,
        @Query("user_id") userId: Int? = null
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

    @POST("/auth/forget-password")
    suspend fun forgetPassword(
        @Body email: User
    ): VerificationResponseDto?

    @POST("auth/change-password")
    suspend fun updateUserPassword(
        @Body passwordData: PasswordData
    ): AuthenticationResponseDto?

    @POST("/auth/reset-password")
    suspend fun resetPassword(
        @Body data: User,
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

    @GET("pieces/museum/{museumId}")
    suspend fun getMuseumPieces(
        @Path("museumId") museumId: Int,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
        @Query("name") name: String? = null,
    ): PiecesResponseDto?

    @GET("/pieces/museum/{id}")
    suspend fun getPiecesOfCollection(
        @Path("id") museumID: Int,
        @Query("name") name: String? = null,
        @Query("collection") collectionID: Int? = null,
        @Query("hall") hallID: Int? = null,
        @Query("ar") ar: Boolean? = null
    ): PiecesResponseDto?


    @Multipart
    @PUT("users/upload-image")
    suspend fun updateUserImage(
        @Part avatar: MultipartBody.Part?
    ): AuthenticationResponseDto?

    @PUT("users/me")
    suspend fun updateUserName(
        @Body name: User?
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

    @Multipart
    @POST("api/v1/classify-image")
    suspend fun CalssifyImage(
        @Part avatar: MultipartBody.Part?
    ): AIResponseDto?

    @GET("museum/{museum_id}/reviews")
    suspend fun getReview(
        @Path("museum_id") museumId: Int,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10,
    ): AllReviewsResponseDto?

    @GET("favorites/museums")
    suspend fun getfavouriteMuseum(
    ): FavouriteMuseumResponseDto

    @POST("favorites/museums/{id}")
    suspend fun postfavouriteMuseums(
        @Path("id") museumId: Int
    ): FavouriteTestDto


    @DELETE("/favorites/museums/{id}")
    suspend fun deletefavouritemuseum(
        @Path("id") museumId: Int
    ): VerificationResponseDto

    @GET("/favorites/pieces")
    suspend fun getFavouritePiece(

    ): FavouritePiecesResponseDto

    @POST("favorites/pieces/{id}")
    suspend fun postfavouritePieces(
        @Path("id") museumId: Int
    ): VerificationResponseDto


    @DELETE("/favorites/pieces/{id}")
    suspend fun deletefavouritePieces(
        @Path("id") museumId: Int
    ): VerificationResponseDto


    @GET("/museums/{id}/stories")
    suspend fun getStoriesOfMuseum(
        @Path("id") museumId: Int
    ): StoriesResponceDto

    @GET("/halls/museums/{id}")
    suspend fun getAllHallsOfMuseum(
        @Path("id") museumId: Int
    ): HallsResponseDto

    @GET("/halls/{id}")
    suspend fun getHallById(
        @Path("id") hallID: Int
    ): HallsResponseDto


    @GET("/museums/{museumId}/collections")
    suspend fun getCollectionOfMuseum(
        @Path("museumId") museumId: Int
    ): CollectionsResponseDto


}