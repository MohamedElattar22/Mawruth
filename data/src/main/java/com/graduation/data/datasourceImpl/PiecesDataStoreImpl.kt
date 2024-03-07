package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.pieces.PiecesDataStore
import com.graduation.domain.model.museumdata.PiecesItemDto
import javax.inject.Inject

class PiecesDataStoreImpl @Inject constructor(private val webServices: WebServices) :
    PiecesDataStore {
    override suspend fun getPieceById(pieceId: Int): PiecesItemDto? {
        val result = webServices.getPieceById(pieceId)
        return result?.toPiecesDto()
    }
}