package com.graduation.data.model.authuserdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.authenticationuser.User

data class AuthenticationUserDto(

    @field:SerializedName("image")
    val image: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
) {
    fun toUser(): User {
        return User(
            image = image.toString(),
            name = name,
            id = id,
            email = email,
            username = username
        )
    }
}