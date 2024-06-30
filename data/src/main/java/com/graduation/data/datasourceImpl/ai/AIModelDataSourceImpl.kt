//package com.graduation.data.datasourceImpl.ai
//
//import android.util.Log
//import com.graduation.data.api.modelapi.ModelWebService
//import com.graduation.data.dataSourceContract.ai.AIModelDataSource
//import com.graduation.domain.model.ai.AIResponce
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import java.io.File
//import javax.inject.Inject
//
//class AIModelDataSourceImpl @Inject constructor(private val  modelWebService: ModelWebService):AIModelDataSource {
//    override suspend fun classifyImage(image: File?): AIResponce {
//        val avatarRB = image?.let {
//            MultipartBody.Part.createFormData(
//                "image",
//                it.name,
//                it.asRequestBody("image/*".toMediaTypeOrNull())
//            )
//        }
//        return modelWebService.CalssifyImage(avatarRB)!!.toCategoriesAIResponse()
//    }
//
//}