package com.graduation.data.model.museums

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museums.MuseumCategory

data class MuseumCategoryDto(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) {
    fun toMuseumCategory(): MuseumCategory {
        return MuseumCategory(
            name, id
        )
    }
}