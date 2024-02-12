package com.graduation.data.model.museum

import com.google.gson.annotations.SerializedName

data class TypesItem(

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
)