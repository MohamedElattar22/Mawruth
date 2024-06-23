package com.graduation.domain.model.authenticationuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val user: User? = null,
    val token: String? = null,
    val message: String? = null,
) : Parcelable