package com.graduation.domain.useCase

import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetPiecesOfCollectionUseCase @Inject constructor(private val piecesRepository: PiecesRepository) {
    suspend fun invoke(
        museumID: Int,
        collectionID: Int? = null,
        hallID: Int? = null,
        pieceName: String? = null,
        ar: Boolean? = null
    ): PiecesResponse? {
        return piecesRepository.getPiecesOfCollection(
            collectionID = collectionID,
            museumID = museumID,
            hallID = hallID,
            pieceName = pieceName,
            ar = ar
        )
    }
}