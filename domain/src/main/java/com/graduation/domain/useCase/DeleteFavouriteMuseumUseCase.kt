package com.graduation.domain.useCase



import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import com.graduation.domain.repositories.FavouritePieceRepository

import javax.inject.Inject

class DeleteFavouriteMuseumUseCase@Inject constructor(private val favouriteMuseumsRepository: FavouriteMuseumsRepository) {
    suspend fun invoke(museumID: Int): VerificationResponse? {
        return favouriteMuseumsRepository.deleteFavouriteMuseums(museumID)
    }
}