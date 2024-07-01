package com.graduation.data.datasourceImpl.ai

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.ai.AIModelDataSource
import com.graduation.domain.model.ai.AIResponce
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Inject

class AIModelDataSourceImpl @Inject constructor() : AIModelDataSource {
    override suspend fun classifyImage(image: File?): AIResponce {
        val avatarRB = image?.let {
            MultipartBody.Part.createFormData(
                "image",
                it.name,
                it.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
        return Retrofit.Builder()
            .baseUrl("http://40.90.236.58:8000/").addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebServices::class.java)
            .CalssifyImage(avatarRB)!!.toCategoriesAIResponse()
    }

}