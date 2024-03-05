package com.graduation.domain.model.museumdata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ImagesItem(
    val imagePath: String? = null,
    val createdAt: String? = null,
    val iD: Int? = null,
    val museumID: Int? = null,
    val deletedAt: @RawValue Any? = null,
    val updatedAt: String? = null,
    val pieceID: Int? = null
) : Parcelable