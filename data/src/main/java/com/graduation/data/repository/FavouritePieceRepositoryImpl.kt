package com.graduation.data.repository

import com.graduation.data.api.WebServices
import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.FavouritePieceRepository
import javax.inject.Inject

class FavouritePieceRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : FavouritePieceRepository{
    override suspend fun getFavouritePieces(): FavouritePieceResponse? {
        return webServices.getFavouritePiece().toFavouritePieceResponse()
    }

    override suspend fun sendFavouritePieces(museumid: Int): VerificationResponse? {
        return webServices.postfavouritePieces(museumid).toVerificationResponse()
    }

    override suspend fun deleteFavouritePieces(museumid: Int): VerificationResponse? {
        return webServices.deletefavouritePieces(museumid).toVerificationResponse()
    }
    }
