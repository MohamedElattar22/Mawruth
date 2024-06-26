package com.graduation.data.dataSourceContract

import com.graduation.data.model.museum.MuseumResponse


interface FavouriteMuseumsDataSource {
   suspend fun getFavouriteMuseums(): MuseumResponse?
   suspend fun sendFavouriteMuseums(museumId: Int): Verificat?
}