package com.graduation.data.model.reviews

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.reviews.ReviewsResponse

data class ReviewsResponseDto(

    @field:SerializedName("data")
    val reviewsDataDto: ReviewsDataDto? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toReviewsResponse(): ReviewsResponse {
        return ReviewsResponse(reviewsDataDto?.toReviews(), status)
    }
}