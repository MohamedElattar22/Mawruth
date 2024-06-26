package com.graduation.data.model.authuserdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.authenticationuser.AuthenticationResponse

data class AuthenticationResponseDto(

    @field:SerializedName("data")
    val data: DataDto? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toAuthenticationResponse(): AuthenticationResponse {
        return AuthenticationResponse(
            data = data?.toData(),
            status = status
        )
    }
}