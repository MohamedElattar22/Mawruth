package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class SendFavouriteUseCase @Inject constructor(private val favouriteMuseumsRepository: FavouriteMuseumsRepository) {
    suspend fun invoke(museumID: Int): VerificationResponse? {
        return favouriteMuseumsRepository.sendFavouriteMuseums(museumID)
    }
}