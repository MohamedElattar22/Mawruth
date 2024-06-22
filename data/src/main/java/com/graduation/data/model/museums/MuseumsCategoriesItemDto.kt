package com.graduation.data.model.museums

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museums.MuseumCategoriesItem

data class MuseumsCategoriesItemDto(

    @field:SerializedName("category")
    val museumCategoryDto: MuseumCategoryDto? = null
) {
    fun toMuseumCategoriesItem(): MuseumCategoriesItem {
        return MuseumCategoriesItem(
            museumCategoryDto?.toMuseumCategory()
        )
    }
}