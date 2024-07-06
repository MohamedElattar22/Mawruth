package com.graduation.data.dataSourceContract.musums

import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.museums.MuseumsResponse

interface MuseumsDataSource {
    suspend fun getAllMuseums(
        page: Int? = null,
        limit: Int? = null,
        name: String? = null,
        category: String? = null,
        city: String? = null,
userId:Int? = null
    ): MuseumsResponse?

    suspend fun getMuseumById(museumId: Int): MuseumItem?

//    suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?>
}