package com.graduation.data.repository

import com.graduation.data.dataSourceContract.pieces.PiecesDataStore
import com.graduation.domain.model.museumdata.PiecesItemDto
import com.graduation.domain.repositories.PiecesRepository
import javax.inject.Inject

class PiecesRepositoryImpl
@Inject constructor(private val piecesDataStore: PiecesDataStore) : PiecesRepository {
    override suspend fun getPieceById(pieceId: Int): PiecesItemDto? {
        return piecesDataStore.getPieceById(pieceId)
    }

}