package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.FavouriteMuseumsDataSource
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.FavouriteTest
import com.graduation.domain.model.VerificationResponse
import javax.inject.Inject

class FavouriteMuseumsDataSourceImpl @Inject constructor(
   val webServices: WebServices
) : FavouriteMuseumsDataSource{
    override suspend fun getFavouriteMuseums(): FavouriteMuseumResponse? {
        return webServices.getfavouriteMuseum().toFavouriteResponse()
    }

    override suspend fun sendFavouriteMuseums(museumId: Int): FavouriteTest {
        return webServices.postfavouriteMuseums(museumId).toFavouriteTest()
    }

    override suspend fun deleteFavouriteMuseums(museumId: Int): VerificationResponse {
        return webServices.deletefavouritemuseum(museumId).toVerificationResponse()
    }

}