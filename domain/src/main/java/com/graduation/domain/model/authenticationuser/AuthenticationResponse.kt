package com.graduation.domain.model.authenticationuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthenticationResponse(
    val data: Data? = null,
    val status: String? = null
) : Parcelable