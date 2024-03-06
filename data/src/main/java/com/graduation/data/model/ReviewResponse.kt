package com.graduation.data.model

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.ReviewDto

data class ReviewResponse(

    @field:SerializedName("creator")
    val creator: Creator? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("museum_id")
    val museumId: Int? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toReviewDto(): ReviewDto {
        return ReviewDto(
            creator = creator?.toCreatorDto(),
            userId = userId,
            rating = rating,
            museumId = museumId,
            content = content,
        )
    }

    companion object {
        fun toReview(reviewDto: ReviewDto): ReviewResponse {
            return ReviewResponse(
                userId = reviewDto.userId,
                museumId = reviewDto.museumId,
                content = reviewDto.content,
                rating = reviewDto.rating
            )
        }
    }
}