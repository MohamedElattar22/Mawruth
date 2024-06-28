package com.graduation.data.repository

import com.graduation.data.dataSourceContract.halls.HallsDataSource
import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.repositories.HallRepository
import javax.inject.Inject

class HallsRepositoryImpl @Inject constructor(private val hallsDataSource: HallsDataSource) :
    HallRepository {
    override suspend fun getAllHallsOfMuseum(museumId: Int): HallsResponse {
        return hallsDataSource.getAllHallsOfMuseum(museumId)
    }

}