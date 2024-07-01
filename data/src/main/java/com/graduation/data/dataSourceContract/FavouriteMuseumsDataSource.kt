package com.graduation.data.dataSourceContract

import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.FavouriteTest
import com.graduation.domain.model.VerificationResponse


interface FavouriteMuseumsDataSource {
   suspend fun getFavouriteMuseums(): FavouriteMuseumResponse?
   suspend fun sendFavouriteMuseums(museumId: Int): FavouriteTest?
   suspend fun deleteFavouriteMuseums(museumId: Int):VerificationResponse?
}