package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.FavouriteMuseumsDataSource
import com.graduation.data.model.museums.MuseumsResponseDto
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.museums.MuseumsResponse
import javax.inject.Inject

class FavouriteMuseumsDataSourceImpl @Inject constructor(
   val webServices: WebServices
) : FavouriteMuseumsDataSource{
    override suspend fun getFavouriteMuseums(): MuseumsResponse {
        return webServices.getfavouriteMuseum().toMuseumsResponse()
    }

    override suspend fun sendFavouriteMuseums(museumId: Int): VerificationResponse {
        return webServices.postfavouriteMuseums(museumId).toVerificationResponse()
    }

}