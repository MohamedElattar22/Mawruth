package com.graduation.data.dataSourceContract.halls

import com.graduation.domain.model.halls.HallsResponse

interface HallsDataSource {
    suspend fun getAllHallsOfMuseum(museumId: Int): HallsResponse
    suspend fun getHallById(hallID: Int): HallsResponse

}