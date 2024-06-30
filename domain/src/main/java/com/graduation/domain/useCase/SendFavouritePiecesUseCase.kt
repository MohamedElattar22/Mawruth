package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse

import com.graduation.domain.repositories.FavouritePieceRepository
import javax.inject.Inject

class SendFavouritePiecesUseCase @Inject constructor(private val favouritePieceRepository: FavouritePieceRepository) {
    suspend fun invoke(museumID: Int): VerificationResponse? {
        return favouritePieceRepository.sendFavouritePieces(museumID)
    }
}