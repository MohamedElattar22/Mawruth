package com.graduation.domain.useCase

import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.repositories.HallRepository
import javax.inject.Inject

class GetAllHallsOfMuseumUseCase @Inject constructor(private val hallRepository: HallRepository) {

    suspend fun invoke(museumId: Int): HallsResponse {
        return hallRepository.getAllHallsOfMuseum(museumId)
    }

}