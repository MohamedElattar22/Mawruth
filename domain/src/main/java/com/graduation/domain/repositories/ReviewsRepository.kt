package com.graduation.domain.repositories

import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse

interface ReviewsRepository {
    suspend fun sendReview(museumId: Int, reviewDto: ReviewsData): ReviewsResponse?
    suspend fun getReview(museumId: Int): AllReviewsResponse?
}