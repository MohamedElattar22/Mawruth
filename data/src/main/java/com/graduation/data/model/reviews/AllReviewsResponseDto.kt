package com.graduation.data.model.reviews

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.reviews.AllReviewsResponse

data class AllReviewsResponseDto(

    @field:SerializedName("data")
    val data: List<ReviewsDataDto?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toAllReviewsResponse(): AllReviewsResponse {
        return AllReviewsResponse(
            data?.map { it?.toReviews() },
            status
        )
    }
}