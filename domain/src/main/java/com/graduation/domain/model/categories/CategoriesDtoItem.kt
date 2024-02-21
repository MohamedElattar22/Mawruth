package com.graduation.domain.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CategoriesDtoItem(
    val image: String? = null,
    val museums: @RawValue Any? = null,
    val createdAt: String? = null,
    val name: String? = null,
    val iD: Int? = null,
    val deletedAt: @RawValue Any? = null,
    val updatedAt: String? = null
) : Parcelable