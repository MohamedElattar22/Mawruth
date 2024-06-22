package com.graduation.domain.useCase

import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class GetMuseumPieces @Inject constructor(private val piecesRepository: PiecesRepository) {

    suspend fun invoke(
        museumId: Int,
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ): PiecesResponse? {
        return piecesRepository.getAllMuseumPieces(museumId, page, limit, name)
    }
}