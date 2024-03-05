package com.graduation.data.model.museumdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museumdata.ImagesItem

data class ImagesItem(

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
    val updatedAt: String? = null,

    @field:SerializedName("PieceID")
    val pieceID: Int? = null
) {
    fun toImageItemDto(): ImagesItem {
        return ImagesItem(
            imagePath = imagePath,
            createdAt = createdAt,
            iD = iD,
            museumID = museumID,
            deletedAt = deletedAt,
            updatedAt = updatedAt,
            pieceID = pieceID
        )
    }
}