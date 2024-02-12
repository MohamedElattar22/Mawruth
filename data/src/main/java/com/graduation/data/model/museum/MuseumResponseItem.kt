package com.graduation.data.model.museum

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museum.MuseumDto

data class MuseumResponseItem(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("types")
    val types: List<TypesItem?>? = null,

    @field:SerializedName("images")
    val images: List<Any?>? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("pieces")
    val pieces: Any? = null,

    @field:SerializedName("reviews")
    val reviews: Any? = null,

    @field:SerializedName("street")
    val street: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("work_time")
    val workTime: String? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null
) {
    fun toMuseumDto(): MuseumDto {
        return MuseumDto(
            country,
            images = images,
            city = city,
            createdAt = createdAt,
            rating = rating,
            description = description,
            updatedAt = updatedAt,
            pieces = pieces,
            reviews = reviews,
            street = street,
            name = name,
            iD = iD,
            workTime = workTime,
            deletedAt = deletedAt
        )
    }
}