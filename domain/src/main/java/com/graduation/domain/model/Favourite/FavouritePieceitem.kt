package com.graduation.domain.model.Favourite

import com.graduation.domain.model.pieces.PiecesItem

data class FavouritePieceitem(
    val piece: PiecesItem? = null,

    val id: Int? = null,

    val userId: Int? = null,

    val pieceId: Int? = null
)
