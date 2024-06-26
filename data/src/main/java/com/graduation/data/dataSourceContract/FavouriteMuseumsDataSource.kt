package com.graduation.data.dataSourceContract

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.museums.MuseumsResponse


interface FavouriteMuseumsDataSource {
   suspend fun getFavouriteMuseums(): MuseumsResponse?
   suspend fun sendFavouriteMuseums(museumId: Int): VerificationResponse?
}