package com.graduation.domain.useCase

import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetPieceByNameUseCase @Inject constructor(private val piecesRepository: PiecesRepository) {
    suspend fun invoke(pieceName: String, museumId: Int): PiecesResponse? {
        return piecesRepository.getPiecesOfCollection(pieceName = pieceName, museumID = museumId)
    }
}