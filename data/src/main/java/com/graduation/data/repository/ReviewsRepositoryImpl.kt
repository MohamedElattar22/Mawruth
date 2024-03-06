package com.graduation.data.repository

import com.graduation.data.dataSourceContract.ReviewsDataSource
import com.graduation.domain.model.ReviewDto
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(private val reviewsDataSource: ReviewsDataSource) :
    ReviewsRepository {
    override suspend fun sendReview(reviewDto: ReviewDto): ReviewDto? {
        return reviewsDataSource.sendReview(reviewDto)
    }

    override suspend fun getReview(museumId: Int): List<ReviewDto?>? {
        return reviewsDataSource.getReview(museumId)
    }
}