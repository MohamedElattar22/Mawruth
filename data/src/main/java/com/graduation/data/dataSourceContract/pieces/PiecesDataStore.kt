package com.graduation.data.dataSourceContract.pieces

import com.graduation.domain.model.museumdata.PiecesItemDto

interface PiecesDataStore {

    suspend fun getPieceById(pieceId: Int): PiecesItemDto?


}