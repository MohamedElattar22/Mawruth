package com.graduation.data.model.museumdata

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.museumdata.MuseumDataDto

data class MuseumDataResponce(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("types")
    val types: List<TypesItem>? = null,

    @field:SerializedName("images")
    val images: List<ImagesItem?>? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("pieces")
    val pieces: List<PiecesItem?>? = null,

    @field:SerializedName("reviews")
    val reviews: List<Any?>? = null,

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
    fun toMuseumItemDto(): MuseumDataDto {
        return MuseumDataDto(
            country = country,
            types = types?.map {
                it.toTypesItemDto()
            },
            pieces = pieces?.map {
                it?.toPiecesDto()
            },
            city = city,
            createdAt = createdAt,
            workTime = workTime,
            images = listOf(images?.get(0)?.toImageItemDto()),
            iD = iD,
            description = description,
            name = name,
            street = street,
            reviews = reviews,
            deletedAt = deletedAt,
            rating = rating,
            updatedAt = updatedAt
        )
    }
}