package com.graduation.domain.repositories

import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.VerificationResponse

interface FavouriteMuseumsRepository  {

    suspend fun getFavouriteMuseums():FavouriteMuseumResponse?
    suspend fun sendFavouriteMuseums(museumid:Int):FavouriteMuseumResponse?
    suspend fun deleteFavouriteMuseums(museumid:Int):VerificationResponse?

}