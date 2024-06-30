package com.graduation.data.model.favourite

import com.google.gson.annotations.SerializedName
import com.graduation.data.model.pieces.PiecesItemDto
import com.graduation.domain.model.Favourite.FavouriteMuseumItem
import com.graduation.domain.model.Favourite.FavouritePieceitem
import com.graduation.domain.model.pieces.PiecesItem

data class FavouritePiecesItemDto(

	@field:SerializedName("piece")
	val piece: PiecesItemDto? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("pieceId")
	val pieceId: Int? = null
){
	fun toFavouriteitem(): FavouritePieceitem {
		return FavouritePieceitem(
			piece?.toPiecesItem() ,
			id,
			userId,pieceId
		)
	}


}