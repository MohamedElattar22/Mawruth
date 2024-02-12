package com.graduation.data.model.userlogin

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.userlogin.UserLoginDto

data class UserLoginResponse(

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("user_name")
    val userName: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("token")
    val token: String? = null
) {
    fun toUserDto(): UserLoginDto {
        return UserLoginDto(
            fullName, userName, id, email, token
        )
    }
}