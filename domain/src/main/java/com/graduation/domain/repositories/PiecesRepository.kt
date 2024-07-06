package com.graduation.domain.repositories

import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.domain.model.pieces.PiecesResponse

interface PiecesRepository {

    suspend fun getPieceById(pieceId: Int): PiecesItem?
    suspend fun getAllMuseumPieces(
        museumId: Int,
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ): PiecesResponse?

    suspend fun getPiecesOfCollection(
        collectionID: Int? = null,
        museumID: Int,
        pieceName: String? = null,
        hallID: Int? = null,
        ar: Boolean? = null
    ): PiecesResponse?


}