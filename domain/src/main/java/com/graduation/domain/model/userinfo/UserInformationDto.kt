package com.graduation.domain.model.userinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserInformationDto(
    val password: String? = null,
    val fullName: String? = null,
    val userName: String? = null,
    val createdAt: String? = null,
    val iD: Int? = null,
    val avatar: String? = null,
    val deletedAt: @RawValue Any? = null,
    val updatedAt: String? = null,
    val email: String? = null
) : Parcelable