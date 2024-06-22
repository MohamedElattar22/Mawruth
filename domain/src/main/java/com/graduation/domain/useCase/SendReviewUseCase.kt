package com.graduation.domain.useCase

import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class SendReviewUseCase @Inject constructor(private val reviewsRepository: ReviewsRepository) {
    suspend fun invoke(museumId: Int, reviewDto: ReviewsData): ReviewsResponse? {
        return reviewsRepository.sendReview(museumId, reviewDto)
    }
}