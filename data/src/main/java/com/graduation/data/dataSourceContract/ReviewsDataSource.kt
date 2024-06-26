package com.graduation.data.dataSourceContract

import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse


interface ReviewsDataSource {
    suspend fun sendReview(museumId: Int, reviewDto: ReviewsData): ReviewsResponse?
    suspend fun getReview(museumId: Int): AllReviewsResponse?
}