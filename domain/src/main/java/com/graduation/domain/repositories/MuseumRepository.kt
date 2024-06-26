package com.graduation.domain.repositories

import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.museums.MuseumsResponse

interface MuseumRepository {
    suspend fun getAllMuseum(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null,
        category: String? = null,
        city: String? = null
    ): MuseumsResponse?

    suspend fun getMuseumById(museumId: Int): MuseumItem?
//    suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?>

}