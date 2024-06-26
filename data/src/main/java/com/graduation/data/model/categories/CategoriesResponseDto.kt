package com.graduation.data.model.categories

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.categories.CategoriesResponse

data class CategoriesResponseDto(

    @field:SerializedName("data")
    val data: List<CategoryItemDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toCategoriesResponse(): CategoriesResponse {
        return CategoriesResponse(data?.map { it?.toCategoryItem() }, status)
    }
}