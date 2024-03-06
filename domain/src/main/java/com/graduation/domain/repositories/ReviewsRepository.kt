package com.graduation.domain.repositories

import com.graduation.domain.model.ReviewDto

interface ReviewsRepository {
    suspend fun sendReview(reviewDto: ReviewDto): ReviewDto?
    suspend fun getReview(museumId: Int): List<ReviewDto?>?
}