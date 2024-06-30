package com.graduation.data.dataSourceContract

import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.pieces.PiecesResponse

interface FavouritePiecesDataSource {
    suspend fun getFavouritePieces() :FavouritePieceResponse?
    suspend fun sendFavouritePieces(museumId: Int): VerificationResponse?
    suspend fun deleteFavouritePieces(museumId: Int): VerificationResponse?
}