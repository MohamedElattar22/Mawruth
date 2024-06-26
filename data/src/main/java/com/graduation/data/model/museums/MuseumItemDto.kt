package com.graduation.data.model.museums

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museums.MuseumItem

data class MuseumItemDto(

    @field:SerializedName("images")
    val images: List<MuseumImagesItemDto?>? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("street")
    val street: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("categories")
    val categories: List<MuseumsCategoriesItemDto?>? = null
) {
    fun toMuseumItem(): MuseumItem {
        return MuseumItem(
            images?.map { it?.toMuseumImagesItem() },
            city,
            street,
            name,
            description,
            id,
            categories?.map { it?.toMuseumCategoriesItem() })
    }
}