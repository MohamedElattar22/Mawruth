//package com.graduation.data.api.modelapi
//
//import com.graduation.data.model.ai.AIResponseDto
//import okhttp3.MultipartBody
//import retrofit2.http.Multipart
//import retrofit2.http.POST
//import retrofit2.http.Part
//
//interface ModelWebService {
//    @Multipart
//    @POST("api/v1/classify-image")
//    suspend fun CalssifyImage(
//        @Part avatar: MultipartBody.Part?
//    ):AIResponseDto?
//
//}