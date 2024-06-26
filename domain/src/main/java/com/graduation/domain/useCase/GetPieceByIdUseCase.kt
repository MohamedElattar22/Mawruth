package com.graduation.domain.useCase

import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetPieceByIdUseCase @Inject constructor(private val piecesRepository: PiecesRepository) {
    suspend fun invoke(pieceId: Int): PiecesItem? {
        return piecesRepository.getPieceById(pieceId)
    }
}