package com.graduation.data.datasourceImpl.halls

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.halls.HallsDataSource
import com.graduation.domain.model.halls.HallsResponse
import javax.inject.Inject

class HallsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    HallsDataSource {
    override suspend fun getAllHallsOfMuseum(museumId: Int): HallsResponse {
        return webServices.getAllHallsOfMuseum(museumId).toHallsResponse()
    }
}