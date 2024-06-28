package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class sendFavouriteMuseumUseCase @Inject constructor(
    private val favouriteMuseumsRepository: FavouriteMuseumsRepository,

    ) {
    suspend fun sendFavouriteMuseums(museumId: Int): VerificationResponse? {
        return favouriteMuseumsRepository.sendFavouriteMuseums(museumId)
    }
}