package com.graduation.data.model.museums

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museums.MuseumsResponse

data class MuseumsResponseDto(

    @field:SerializedName("data")
    val data: List<MuseumItemDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toMuseumsResponse(): MuseumsResponse {
        return MuseumsResponse(
            data?.map { it?.toMuseumItem() },
            status
        )
    }
}