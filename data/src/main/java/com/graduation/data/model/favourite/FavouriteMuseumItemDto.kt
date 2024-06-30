package com.graduation.data.model.favourite

import com.google.gson.annotations.SerializedName
import com.graduation.data.model.museums.MuseumItemDto
import com.graduation.domain.model.Favourite.FavouriteMuseumItem

data class FavouriteMuseumItemDto(

	@field:SerializedName("museumId")
	val museumId: Int? = null,

	@field:SerializedName("museum")
	val museum: MuseumItemDto? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
){
	fun toFavouriteitem(): FavouriteMuseumItem{
		return FavouriteMuseumItem(
			museumId,
			museum?.toMuseumItem(),
			id,
			userId
		)
	}


}