package com.graduation.domain.useCase

import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class SendFavouriteMuseumUseCase@Inject constructor(
    private  val favouriteMuseumsRepository: FavouriteMuseumsRepository,

    ) {
    suspend fun sendFavouriteMuseums(MuseumId : Int): FavouriteMuseumResponse? {
        return favouriteMuseumsRepository.sendFavouriteMuseums(MuseumId)
    }

}