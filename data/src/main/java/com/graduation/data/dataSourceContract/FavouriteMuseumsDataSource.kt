package com.graduation.data.dataSourceContract

import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.VerificationResponse


interface FavouriteMuseumsDataSource {
   suspend fun getFavouriteMuseums(): FavouriteMuseumResponse?
   suspend fun sendFavouriteMuseums(museumId: Int): FavouriteMuseumResponse?
   suspend fun deleteFavouriteMuseums(museumId: Int):VerificationResponse?
}