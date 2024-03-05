package com.graduation.domain.model.museumdata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MuseumDataDto(
    val country: String? = null,
    val types: List<TypesItem?>? = null,
    val images: List<ImagesItem?>? = null,
    val city: String? = null,
    val createdAt: String? = null,
    val rating: Int? = null,
    val description: String? = null,
    val updatedAt: String? = null,
    val pieces: List<PiecesItem?>? = null,
    val reviews: @RawValue List<Any?>? = null,
    val street: String? = null,
    val name: String? = null,
    val iD: Int? = null,
    val workTime: String? = null,
    val deletedAt: @RawValue Any? = null
) : Parcelable