package com.graduation.domain.repositories

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.museums.MuseumsResponse

interface FavouriteMuseumsRepository  {

    suspend fun getFavouriteMuseums():MuseumsResponse?
    suspend fun sendFavouriteMuseums(museumid:Int):VerificationResponse?
}