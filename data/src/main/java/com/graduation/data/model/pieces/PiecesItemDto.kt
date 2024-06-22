package com.graduation.data.model.pieces

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.pieces.PiecesItem

data class PiecesItemDto(

    @field:SerializedName("museumId")
    val museumId: Int? = null,

    @field:SerializedName("soundPath")
    val soundPath: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("arPath")
    val arPath: String? = null,

    @field:SerializedName("hallId")
    val hallId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("isMasterpiece")
    val isMasterpiece: Boolean? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("age")
    val age: String? = null
) {
    fun toPiecesItem(): PiecesItem {
        return PiecesItem(
            museumId, soundPath, image, arPath, hallId, name, description, isMasterpiece, id, age
        )
    }
}