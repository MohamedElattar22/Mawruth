package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.FavouritePiecesDataSource
import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.VerificationResponse
import javax.inject.Inject

class FavouritePiecesDataSourceImpl@Inject constructor(
    val webServices: WebServices
) :FavouritePiecesDataSource {
    override suspend fun getFavouritePieces(): FavouritePieceResponse? {
      return webServices.getFavouritePiece().toFavouritePieceResponse()
    }

    override suspend fun sendFavouritePieces(museumId: Int): VerificationResponse? {
        return webServices.postfavouritePieces(museumId).toVerificationResponse()
    }

    override suspend fun deleteFavouritePieces(museumId: Int): VerificationResponse? {
       return webServices.deletefavouritePieces(museumId).toVerificationResponse()
    }
}