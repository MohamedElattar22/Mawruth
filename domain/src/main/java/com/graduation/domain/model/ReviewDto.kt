package com.graduation.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ReviewDto(

    val creator: CreatorDto? = null,

    val userId: Int? = null,

    val rating: @RawValue Any? = null,

    val museumId: Int? = null,

    val content: String? = null
) : Parcelable