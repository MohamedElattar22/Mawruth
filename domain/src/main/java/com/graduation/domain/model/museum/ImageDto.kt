package com.graduation.domain.model.museum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ImageDto(
    val imagePath: String? = null,
    val createdAt: String? = null,
    val iD: Int? = null,
    val museumID: Int? = null,
    val deletedAt: @RawValue Any? = null,
    val updatedAt: String? = null
) : Parcelable
