package com.graduation.domain.model.museum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MuseumDto(
    val country: String? = null,
    val types: List<TypesItem?>? = null,
    val images: @RawValue List<Any?>? = null,
    val city: String? = null,
    val createdAt: String? = null,
    val rating: @RawValue Any? = null,
    val description: String? = null,
    val updatedAt: String? = null,
    val pieces: @RawValue Any? = null,
    val reviews: @RawValue Any? = null,
    val street: String? = null,
    val name: String? = null,
    val iD: Int? = null,
    val workTime: String? = null,
    val deletedAt: @RawValue Any? = null
) : Parcelable