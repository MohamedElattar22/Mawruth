package com.graduation.data.dataSourceContract.musums

import com.graduation.domain.model.museum.MuseumDto

interface MuseumsDataSource {
    suspend fun getMuseums(): List<MuseumDto?>?
}