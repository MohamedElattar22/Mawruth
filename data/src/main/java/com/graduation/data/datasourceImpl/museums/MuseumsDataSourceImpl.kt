package com.graduation.data.datasourceImpl.museums

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.museums.MuseumsResponse
import javax.inject.Inject

class MuseumsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    MuseumsDataSource {
    override suspend fun getAllMuseums(
        page: Int?,
        limit: Int?,
        name: String?,
        category: String?,
        city: String?,
    ): MuseumsResponse? {
        return webServices.getAllMuseums(page, limit, name, category, city)?.toMuseumsResponse()
    }

    override suspend fun getMuseumById(museumId: Int): MuseumItem? {
        val result = webServices.getMuseumById(museumId)?.toMuseumItem()
        return result
    }

//    override suspend fun getMuseumsByType(typeId: Int): List<MuseumDataDto?> {
//        val result = webServices.getMuseumByType(typeId.toString())
//        return result.map {
//            it?.toMuseumItemDto()
//        }
//    }
}