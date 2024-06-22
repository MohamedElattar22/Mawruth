package com.graduation.data.model.pieces

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.pieces.PiecesResponse

data class PiecesResponseDto(

    @field:SerializedName("data")
    val data: List<PiecesItemDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toPiecesResponse(): PiecesResponse {
        return PiecesResponse(
            data?.map { it?.toPiecesItem() },
            status
        )
    }
}