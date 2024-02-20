package com.graduation.data.model.categories

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.categories.CategoriesDtoItem

data class CategoriesResponseItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("museums")
    val museums: Any? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null
) {
    fun toCategoryDTO(): CategoriesDtoItem {
        return CategoriesDtoItem(
            image = image,
            museums = image,
            createdAt = createdAt,
            name = name,
            iD = iD,
            deletedAt = deletedAt,
            updatedAt = updatedAt
        )
    }
}