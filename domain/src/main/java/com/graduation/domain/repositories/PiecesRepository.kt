package com.graduation.domain.repositories

import com.graduation.domain.model.museumdata.PiecesItemDto

interface PiecesRepository {

    suspend fun getPieceById(pieceId: Int): PiecesItemDto?

}