package com.graduation.domain.model.reviews

data class AllReviewsResponse(
    val data: List<ReviewsData?>?,
    val status: String? = null
)
