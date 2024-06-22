package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.ReviewsDataSource
import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse
import javax.inject.Inject

class ReviewsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    ReviewsDataSource {
    override suspend fun sendReview(museumId: Int, reviewDto: ReviewsData): ReviewsResponse? {
        return webServices.sendReview(museumId, reviewDto)?.toReviewsResponse()
    }

    override suspend fun getReview(museumId: Int): AllReviewsResponse? {
        return webServices.getReview(museumId)?.toAllReviewsResponse()
    }
}