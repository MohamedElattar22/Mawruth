package com.graduation.data.model.stories

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.stories.StoryData

data class StoryDataDto(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("museumId")
    val museumId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toStoryData(): StoryData {
        return StoryData(
            image = image,
            museumId = museumId,
            name = name,
            id = id,
            content = content
        )
    }
}