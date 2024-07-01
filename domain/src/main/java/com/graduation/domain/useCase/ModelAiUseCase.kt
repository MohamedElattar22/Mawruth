package com.graduation.domain.useCase

import com.graduation.domain.model.ai.AIResponce
import com.graduation.domain.repositories.AIModelRepository
import java.io.File
import javax.inject.Inject

class ModelAiUseCase @Inject constructor(private val aiModelRepository: AIModelRepository) {
    suspend fun invoke(image: File?): AIResponce? {
        return aiModelRepository.classifyImage(image)
    }
}