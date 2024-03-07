package com.graduation.domain.useCase

import com.graduation.domain.model.museumdata.PiecesItemDto
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetPieceByIdUseCase @Inject constructor(private val piecesRepository: PiecesRepository) {
    suspend fun invoke(pieceId: Int): PiecesItemDto? {
        return piecesRepository.getPieceById(pieceId)
    }
}