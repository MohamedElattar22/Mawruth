package com.graduation.domain.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesDto(
    val categoriesDto: List<CategoriesDtoItem?>? = null
) : Parcelable