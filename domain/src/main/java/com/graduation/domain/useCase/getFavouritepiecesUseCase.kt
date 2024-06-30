package com.graduation.domain.useCase

import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.FavouritePieceRepository
import javax.inject.Inject

class getFavouritepiecesUseCase @Inject constructor(
    private val favouritePieceRepository: FavouritePieceRepository
) {
    suspend fun getFavouritePieces(): FavouritePieceResponse? {
      return favouritePieceRepository.getFavouritePieces()
    }
}