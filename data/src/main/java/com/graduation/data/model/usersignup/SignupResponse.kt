package com.graduation.data.model.usersignup

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.signupdata.User

data class SignupResponse(
    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("deletedAt")
    val deletedAt: String? = null,

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("phone_number")
    val phoneNumber: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
) {
    fun toUser(): User {
        return User(
            createdAt = createdAt,
            password = password,
            deletedAt = deletedAt,
            fullName = fullName,
            phoneNumber = phoneNumber,
            iD = iD,
            email = email,
            username = username,
            updatedAt = updatedAt

        )
    }
}
