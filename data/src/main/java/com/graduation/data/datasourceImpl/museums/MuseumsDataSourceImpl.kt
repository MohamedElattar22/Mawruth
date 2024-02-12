package com.graduation.data.datasourceImpl.museums

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.domain.model.museum.MuseumDto
import javax.inject.Inject

class MuseumsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    MuseumsDataSource {
    override suspend fun getMuseums(): List<MuseumDto?>? {
        val result = webServices.getMuseums()?.map {
            it?.toMuseumDto()
        }
        return result
    }
}