package com.graduation.data.repository

import com.graduation.data.datasourceImpl.ai.AIModelDataSourceImpl
import com.graduation.domain.model.ai.AIResponce
import com.graduation.domain.repositories.AIModelRepository
import java.io.File
import javax.inject.Inject

class AIModelRepositoryImpl @Inject constructor(private val aiModelDataSourceImpl: AIModelDataSourceImpl) :
    AIModelRepository {
    override suspend fun classifyImage(image: File?): AIResponce {


        return aiModelDataSourceImpl.classifyImage(image)

    }
}