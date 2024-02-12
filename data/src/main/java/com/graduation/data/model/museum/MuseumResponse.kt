package com.graduation.data.model.museum

import com.google.gson.annotations.SerializedName

data class MuseumResponse(
    @field:SerializedName("MuseumResponse")
    val museumResponse: List<MuseumResponseItem?>? = null
)