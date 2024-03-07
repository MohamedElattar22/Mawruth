package com.graduation.data.model.museumdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museumdata.PiecesItemDto

data class PiecesItem(

    @field:SerializedName("master_piece")
    val masterPiece: Boolean? = null,

    @field:SerializedName("images")
    val images: List<ImagesItem?>? = null,

    @field:SerializedName("category_id")
    val categoryId: Int? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("ar_path")
    val arPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("museum_id")
    val museumId: Int? = null
) {
    fun toPiecesDto(): PiecesItemDto {
        return PiecesItemDto(
            masterPiece = masterPiece,
            images = images?.map {
                it?.toImageItemDto()
            },
            categoryId = categoryId,
            createdAt = createdAt,
            arPath = arPath,
            name = name,
            description = description,
            iD = iD,
            deletedAt = deletedAt,
            updatedAt = updatedAt
        )

    }
}