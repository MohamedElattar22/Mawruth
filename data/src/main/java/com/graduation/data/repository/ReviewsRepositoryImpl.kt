package com.graduation.data.repository

import com.graduation.data.dataSourceContract.ReviewsDataSource
import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse
import com.graduation.domain.repositories.ReviewsRepository
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(private val reviewsDataSource: ReviewsDataSource) :
    ReviewsRepository {
    override suspend fun sendReview(museumId: Int, reviewDto: ReviewsData): ReviewsResponse? {
        return reviewsDataSource.sendReview(museumId, reviewDto)
    }

    override suspend fun getReview(museumId: Int): AllReviewsResponse? {
        return reviewsDataSource.getReview(museumId)
    }
}