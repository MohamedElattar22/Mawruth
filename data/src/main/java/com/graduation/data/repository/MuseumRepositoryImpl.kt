package com.graduation.data.repository

import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.model.museumdata.MuseumDataDto
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(private val museumsDataSource: MuseumsDataSource) :
    MuseumRepository {
    override suspend fun getMuseum(): List<MuseumDto?>? {
        return museumsDataSource.getMuseums()
    }

    override suspend fun getMuseumById(museumId: Int): MuseumDataDto? {
        return museumsDataSource.getMuseumById(museumId)
    }

    override suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?> {
        return museumsDataSource.getMuseumsByType(typeId)
    }
}