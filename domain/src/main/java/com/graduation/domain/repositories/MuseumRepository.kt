package com.graduation.domain.repositories

import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.model.museumdata.MuseumDataDto

interface MuseumRepository {
    suspend fun getMuseum(): List<MuseumDto?>?
    suspend fun getMuseumById(museumId: Int): MuseumDataDto?
    suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?>

}