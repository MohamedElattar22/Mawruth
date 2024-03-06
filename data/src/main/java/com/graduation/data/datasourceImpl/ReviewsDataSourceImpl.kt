package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.ReviewsDataSource
import com.graduation.data.model.ReviewResponse
import com.graduation.domain.model.ReviewDto
import javax.inject.Inject

class ReviewsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    ReviewsDataSource {
    override suspend fun sendReview(reviewDto: ReviewDto): ReviewDto? {
        val review = ReviewResponse.toReview(reviewDto)
        return webServices.sendReview(review)?.toReviewDto()
    }

    override suspend fun getReview(museumId: Int): List<ReviewDto?>? {
        return webServices.getReview(museumId)?.map {
            it?.toReviewDto()
        }
    }
}