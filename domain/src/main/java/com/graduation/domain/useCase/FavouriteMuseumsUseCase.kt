package com.graduation.domain.useCase


import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class FavouriteMuseumsUseCase @Inject constructor(
 private  val favouriteMuseumsRepository: FavouriteMuseumsRepository,

) {
    suspend fun getFavouriteMuseums(): FavouriteMuseumResponse? {
        return favouriteMuseumsRepository.getFavouriteMuseums()
    }

}