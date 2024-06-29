package com.graduation.data.model.collection

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.collection.CollectionsResponse

data class CollectionsResponseDto(

    @field:SerializedName("data")
    val data: List<CollectionsItemDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toCollectionsResponse(): CollectionsResponse {
        return CollectionsResponse(data?.map { it?.toCollectionsItem() }, status)
    }
}