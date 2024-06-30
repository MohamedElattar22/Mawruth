package com.graduation.data.model.collection

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.collection.CollectionsItem

data class CollectionsItemDto(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("museumId")
    val museumId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) {
    fun toCollectionsItem(): CollectionsItem {
        return CollectionsItem(image = image, museumId = museumId, name = name, id = id)
    }
}