package com.graduation.domain.model.userlogin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLoginDto(
    val fullName: String? = null,
    val userName: String? = null,
    val id: Int? = null,
    val email: String? = null,
    val token: String? = null
) : Parcelable
