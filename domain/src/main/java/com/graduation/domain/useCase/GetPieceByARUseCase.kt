package com.graduation.domain.useCase

import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetPieceByARUseCase @Inject constructor(private val piecesRepository: PiecesRepository) {
    suspend fun invoke(museumId: Int, hasAR: Boolean): PiecesResponse? {
        return piecesRepository.getPiecesOfCollection(museumID = museumId, ar = hasAR)
    }
}