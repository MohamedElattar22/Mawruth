//package com.graduation.data.repository
//
//import com.graduation.data.datasourceImpl.ai.AIModelDataSourceImpl
//import com.graduation.domain.model.ai.AIResponce
//import com.graduation.domain.repositories.AIModelRepository
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import java.io.File
//import javax.inject.Inject
//
//class AIModelRepositoryImpl @Inject constructor(private val aiModelDataSourceImpl: AIModelDataSourceImpl):AIModelRepository {
//    override suspend fun classifyImage(image: File?): AIResponce? {
//
//
//            return aiModelDataSourceImpl.classifyImage(image)
//
//    }
//}