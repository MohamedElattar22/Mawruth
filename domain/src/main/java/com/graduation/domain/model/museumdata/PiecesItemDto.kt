package com.graduation.domain.model.museumdata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PiecesItemDto(
    val masterPiece: Boolean? = null,
    val images: List<ImagesItem?>? = null,
    val categoryId: Int? = null,
    val createdAt: String? = null,
    val arPath: String? = null,
    val name: String? = null,
    val description: String? = null,
    val iD: Int? = null,
    val deletedAt: @RawValue Any? = null,
    val updatedAt: String? = null,
    val museumId: Int? = null
) : Parcelable