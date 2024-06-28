package com.graduation.domain.repositories

import com.graduation.domain.model.halls.HallsResponse

interface HallRepository {
    suspend fun getAllHallsOfMuseum(museumId: Int): HallsResponse
}