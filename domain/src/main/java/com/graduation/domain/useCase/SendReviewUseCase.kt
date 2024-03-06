package com.graduation.domain.useCase

import com.graduation.domain.model.ReviewDto
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class SendReviewUseCase @Inject constructor(private val reviewsRepository: ReviewsRepository) {
    suspend fun invoke(reviewDto: ReviewDto): ReviewDto? {
        return reviewsRepository.sendReview(reviewDto)
    }
}