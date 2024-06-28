package com.graduation.data.model.halls

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.halls.HallsResponse


data class HallsResponseDto(

    @field:SerializedName("data")
    val data: List<HallItemDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toHallsResponse(): HallsResponse {
        return HallsResponse(
            data?.map {
                it?.toHallItem()
            }, status
        )
    }
}