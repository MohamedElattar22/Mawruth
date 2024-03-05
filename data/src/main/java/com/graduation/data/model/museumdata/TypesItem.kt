package com.graduation.data.model.museumdata

import com.google.gson.annotations.SerializedName

data class TypesItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("museums")
    val museums: Any? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null
) {
    fun toTypesItemDto(): com.graduation.domain.model.museumdata.TypesItem {
        return com.graduation.domain.model.museumdata.TypesItem(
            image = image,
            museums = museums,
            createdAt = createdAt,
            name = name,
            iD = iD,
            deletedAt = deletedAt, updatedAt = updatedAt
        )
    }
}