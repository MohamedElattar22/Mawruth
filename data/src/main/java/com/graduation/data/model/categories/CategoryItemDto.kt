package com.graduation.data.model.categories

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.categories.CategoryItem

data class CategoryItemDto(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) {
    fun toCategoryItem(): CategoryItem {
        return CategoryItem(image, name, id)
    }
}