package com.graduation.data.model.museums

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museums.MuseumImagesItem

data class MuseumImagesItemDto(

    @field:SerializedName("image_path")
    val imagePath: String? = null
) {
    fun toMuseumImagesItem(): MuseumImagesItem {
        return MuseumImagesItem(
            imagePath
        )
    }
}