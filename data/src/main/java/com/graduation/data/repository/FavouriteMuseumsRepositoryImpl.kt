package com.graduation.data.repository

import com.graduation.data.api.WebServices
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class FavouriteMuseumsRepositoryImpl @Inject constructor(
    val webServices: WebServices
) :FavouriteMuseumsRepository {
    override suspend fun getFavouriteMuseums(): FavouriteMuseumResponse? {
       return webServices.getfavouriteMuseum().toFavouriteResponse()
    }

    override suspend fun sendFavouriteMuseums(museumid: Int): FavouriteMuseumResponse? {
        return webServices.postfavouriteMuseums(museumid).toFavouriteResponse()
    }

    override suspend fun deleteFavouriteMuseums(museumid: Int): VerificationResponse? {
        return webServices.deletefavouritemuseum(museumid).toVerificationResponse()

    }
}