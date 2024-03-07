package com.graduation.data.dataSourceContract.musums

import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.model.museumdata.MuseumDataDto

interface MuseumsDataSource {
    suspend fun getMuseums(): List<MuseumDto?>?
    suspend fun getMuseumById(museumId: Int): MuseumDataDto?

    suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?>
}