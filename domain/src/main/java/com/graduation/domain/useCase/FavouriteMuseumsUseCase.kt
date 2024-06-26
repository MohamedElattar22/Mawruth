package com.graduation.domain.useCase

import com.graduation.domain.model.museum.MuseumsResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class FavouriteMuseumsUseCase @Inject constructor(
 private  val favouriteMuseumsRepository: FavouriteMuseumsRepository,

) {
 suspend   fun getFavouriteMuseums() : MuseumsResponse? {
        return favouriteMuseumsRepository.getFavouriteMuseums()
    }

}