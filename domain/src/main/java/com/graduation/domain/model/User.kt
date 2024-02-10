package com.graduation.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class User(

    val createdAt: String? = null,
    val password: String? = null,
    val deletedAt: String? = null,
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val iD: Int? = null,
    val email: String? = null,
    val username: String? = null,
    val updatedAt: String? = null

) : Parcelable
