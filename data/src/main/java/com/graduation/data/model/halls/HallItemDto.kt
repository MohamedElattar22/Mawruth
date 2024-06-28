package com.graduation.data.model.halls


import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.halls.HallItem


data class HallItemDto(

    @field:SerializedName("soundPath")
    val soundPath: String? = null,

    @field:SerializedName("soundImage")
    val soundImage: String? = null,

    @field:SerializedName("image_path")
    val imagePath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("museum_id")
    val museumId: Int? = null
) {
    fun toHallItem(): HallItem {
        return HallItem(
            soundPath = soundPath,
            soundImage = soundImage,
            imagePath = imagePath,
            name = name,
            description = description,
            id = id,
            museumId = museumId
        )
    }
}