package com.graduation.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatorDto(

    val password: String? = null,

    val fullName: String? = null,

    val userName: String? = null,

    val iD: Int? = null,

    val avatar: String? = null,

    val updatedAt: String? = null,
    val email: String? = null
) : Parcelable