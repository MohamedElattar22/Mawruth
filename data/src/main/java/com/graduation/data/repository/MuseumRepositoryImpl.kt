package com.graduation.data.repository

import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class MuseumRepositoryImpl @Inject constructor(private val museumsDataSource: MuseumsDataSource) :
    MuseumRepository {
    override suspend fun getAllMuseum(
        page: Int,
        limit: Int,
        name: String?,
        category: String?,
        city: String?
    ): MuseumsResponse? {
        return museumsDataSource.getAllMuseums(page, limit, name, category, city)
    }

    override suspend fun getMuseumById(museumId: Int): MuseumItem? {
        return museumsDataSource.getMuseumById(museumId)
    }

//    override suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?> {
//        return museumsDataSource.getMuseumsByType(typeId)
//    }
}