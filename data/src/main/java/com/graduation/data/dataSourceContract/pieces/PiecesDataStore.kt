package com.graduation.data.dataSourceContract.pieces

import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.domain.model.pieces.PiecesResponse

interface PiecesDataStore {

    suspend fun getPieceById(pieceId: Int): PiecesItem?
    suspend fun getMuseumPieces(
        museumId: Int,
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ): PiecesResponse?

    suspend fun getPiecesOfCollection(collectionID: Int, museumID: Int): PiecesResponse?
}