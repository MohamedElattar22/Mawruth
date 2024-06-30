package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.pieces.PiecesDataStore
import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.domain.model.pieces.PiecesResponse
import javax.inject.Inject

class PiecesDataStoreImpl @Inject constructor(private val webServices: WebServices) :
    PiecesDataStore {
    override suspend fun getPieceById(pieceId: Int): PiecesItem? {
        val result = webServices.getPieceById(pieceId)
        return result?.toPiecesItem()
    }

    override suspend fun getMuseumPieces(
        museumId: Int,
        page: Int,
        limit: Int,
        name: String?
    ): PiecesResponse? {
        return webServices.getMuseumPieces(museumId, page, limit, name)?.toPiecesResponse()
    }

    override suspend fun getPiecesOfCollection(
        collectionID: Int?,
        museumID: Int,
        hallID: Int?,
        ar: Boolean?
    ): PiecesResponse? {
        return webServices.getPiecesOfCollection(
            museumID, collectionID, hallID, ar
        )?.toPiecesResponse()
    }


}