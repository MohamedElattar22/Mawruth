package com.graduation.domain.repositories

import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.pieces.PiecesResponse

interface FavouritePieceRepository {
    suspend fun getFavouritePieces():FavouritePieceResponse?
    suspend fun sendFavouritePieces(museumid:Int): VerificationResponse?
    suspend fun deleteFavouritePieces(museumid:Int): VerificationResponse?
}