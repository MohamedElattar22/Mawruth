package com.graduation.data.model.museum

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museum.ImageDto

data class ImageRersponse(

	@field:SerializedName("image_path")
	val imagePath: String? = null,

	@field:SerializedName("CreatedAt")
	val createdAt: String? = null,

	@field:SerializedName("ID")
	val iD: Int? = null,

	@field:SerializedName("MuseumID")
	val museumID: Int? = null,

	@field:SerializedName("DeletedAt")
	val deletedAt: Any? = null,

	@field:SerializedName("UpdatedAt")
	val updatedAt: String? = null
) {
	fun toImageDto(): ImageDto {
		return ImageDto(
			imagePath = imagePath,
			createdAt = createdAt,
			iD = iD,
			museumID = museumID,
			deletedAt = deletedAt,
			updatedAt = updatedAt
		)
	}
}
