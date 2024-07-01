package com.graduation.data.model.favourite

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.FavouriteTest

class FavouriteTestDto(
    @field:SerializedName("data")
    val data: FavouriteMuseumItemDto? = null,
    @field:SerializedName("status")
    val status: String? = null
) {
    fun toFavouriteTest(): FavouriteTest {
        return FavouriteTest(
            data?.toFavouriteitem(),
            status
        )
    }
}