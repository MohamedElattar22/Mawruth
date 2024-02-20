package com.graduation.data.model.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(

    @field:SerializedName("CategoriesResponse")
    val categoriesResponse: List<CategoriesResponseItem?>? = null
)