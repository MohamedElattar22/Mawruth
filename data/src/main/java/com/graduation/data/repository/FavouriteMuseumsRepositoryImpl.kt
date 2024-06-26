package com.graduation.data.repository

import com.graduation.data.api.WebServices
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class FavouriteMuseumsRepositoryImpl @Inject constructor(
    val webServices: WebServices
) :FavouriteMuseumsRepository {
    override suspend fun getFavouriteMuseums(): MuseumsResponse? {
       return webServices.getfavouriteMuseum().toMuseumsResponse()
    }

    override suspend fun sendFavouriteMuseums(museumid: Int): VerificationResponse? {
        return webServices.postfavouriteMuseums(museumid).toVerificationResponse()
    }
}