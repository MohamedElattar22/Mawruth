package com.graduation.data.dataSourceContract.musums

import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.museums.MuseumsResponse

interface MuseumsDataSource {
    suspend fun getAllMuseums(
        page: Int? = 1,
        limit: Int? = 10,
        name: String? = null,
        category: String? = null,
        city: String? = null,
    ): MuseumsResponse?

    suspend fun getMuseumById(museumId: Int): MuseumItem?

//    suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?>
}