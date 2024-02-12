package com.graduation.domain.repositories

import com.graduation.domain.model.museum.MuseumDto

interface MuseumRepository {
    suspend fun getMuseum(): List<MuseumDto?>?
}