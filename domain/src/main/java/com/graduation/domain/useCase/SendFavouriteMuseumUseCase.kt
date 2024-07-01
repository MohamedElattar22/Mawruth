package com.graduation.domain.useCase

import com.graduation.domain.model.FavouriteTest
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class SendFavouriteMuseumUseCase @Inject constructor(
    private  val favouriteMuseumsRepository: FavouriteMuseumsRepository,

    ) {
    suspend fun sendFavouriteMuseums(MuseumId: Int): FavouriteTest? {
        return favouriteMuseumsRepository.sendFavouriteMuseums(MuseumId)
    }

}