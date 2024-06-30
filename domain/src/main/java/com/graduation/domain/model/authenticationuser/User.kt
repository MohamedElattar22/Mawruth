package com.graduation.domain.model.authenticationuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val password: String? = null,
    val name: String? = null,
    val email: String? = null,
    val username: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val otp: String? = null,
) : Parcelable
