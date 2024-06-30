package com.graduation.data.model.ai

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.ai.AIResponce

data class AIResponseDto(

    @field:SerializedName("result")
    val result: String? = null
) {
    fun toCategoriesAIResponse(): AIResponce {
        return AIResponce(result = result)
    }
}