package com.graduation.domain.useCase

import com.graduation.domain.model.ReviewDto
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(private val reviewsRepository: ReviewsRepository) {
    suspend fun invoke(museumId: Int): List<ReviewDto?>? {
        return reviewsRepository.getReview(museumId)
    }
}