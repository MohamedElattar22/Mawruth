package com.graduation.data.model.favourite

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.Favourite.FavouritePieceResponse

data class FavouritePiecesResponseDto(

	@field:SerializedName("data")
	val data: List<FavouritePiecesItemDto?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) {
	fun toFavouritePieceResponse(): FavouritePieceResponse? {
		return FavouritePieceResponse(
			data?.map { it?.toFavouriteitem() },
			status
		)
	}
}