package com.graduation.data.model.reviews

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.reviews.ReviewsData

data class ReviewsDataDto(

    @field:SerializedName("museumId")
    val museumId: Int? = null,

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toReviews(): ReviewsData {
        return ReviewsData(museumId, rating, id, userId, content)
    }
}