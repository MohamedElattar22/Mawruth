package com.graduation.data.model.stories

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.stories.StoriesResponse

data class StoriesResponceDto(

    @field:SerializedName("data")
    val data: List<StoryDataDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toStoryResponse(): StoriesResponse {
        return StoriesResponse(
            data?.map {
                it?.toStoryData()
            }, status
        )
    }
}