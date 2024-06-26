package com.graduation.domain.useCase


import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class FavouriteMuseumsUseCase @Inject constructor(
 private  val favouriteMuseumsRepository: FavouriteMuseumsRepository,

) {
    suspend fun getFavouriteMuseums(): MuseumsResponse? {
        return favouriteMuseumsRepository.getFavouriteMuseums()
    }

}