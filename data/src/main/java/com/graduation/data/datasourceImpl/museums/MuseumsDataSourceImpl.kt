package com.graduation.data.datasourceImpl.museums

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.model.museumdata.MuseumDataDto
import javax.inject.Inject

class MuseumsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    MuseumsDataSource {
    override suspend fun getMuseums(): List<MuseumDto?>? {
        val result = webServices.getMuseums()?.map {
            it?.toMuseumDto()
        }
        return result
    }

    override suspend fun getMuseumById(museumId: Int): MuseumDataDto? {
        val res = webServices.getMuseumById(museumId)
        return res?.toMuseumItemDto()
    }
}