package com.graduation.data.model

import com.google.gson.annotations.SerializedName
import com.graduation.data.model.authuserdata.DataDto
import com.graduation.domain.model.VerificationResponse

data class VerificationResponseDto(

    @field:SerializedName("data")
    val data: DataDto? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toVerificationResponse(): VerificationResponse {
        return VerificationResponse(
            status = status,
            message = data?.message
        )
    }
}


