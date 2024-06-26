package com.graduation.data.model.authuserdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.authenticationuser.Data

data class DataDto(

    @field:SerializedName("user")
    val user: AuthenticationUserDto? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("message")
    val message: String? = null,
) {
    fun toData(): Data {
        return Data(
            token = token,
            message = message,
            user = user?.toUser()
        )
    }
}