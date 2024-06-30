package com.graduation.data.model.favourite

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse

data class FavouriteMuseumResponseDto(

	@field:SerializedName("data")
	val data: List<FavouriteMuseumItemDto?>? = null,

	@field:SerializedName("status")
	val status: String? = null
){
	fun toFavouriteResponse(): FavouriteMuseumResponse? {
		return FavouriteMuseumResponse(
			data?.map { it?.toFavouriteitem() },
			status
		)
	}
}