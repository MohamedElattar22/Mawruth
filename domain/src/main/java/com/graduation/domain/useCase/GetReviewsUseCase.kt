package com.graduation.domain.useCase

import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(private val reviewsRepository: ReviewsRepository) {
    suspend fun invoke(museumId: Int): AllReviewsResponse? {
        return reviewsRepository.getReview(museumId)
    }
}