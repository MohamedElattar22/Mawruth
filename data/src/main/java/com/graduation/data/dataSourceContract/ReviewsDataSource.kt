package com.graduation.data.dataSourceContract

import com.graduation.domain.model.ReviewDto

interface ReviewsDataSource {
    suspend fun sendReview(reviewDto: ReviewDto): ReviewDto?
    suspend fun getReview(museumId: Int): List<ReviewDto?>?
}