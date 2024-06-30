package com.graduation.domain.model.Favourite

import com.graduation.domain.model.museums.MuseumItem

data class FavouriteMuseumItem(
    val museumId: Int? = null,

    val museum: MuseumItem? = null,
    val id: Int? = null,

    val userId: Int? = null,

)
