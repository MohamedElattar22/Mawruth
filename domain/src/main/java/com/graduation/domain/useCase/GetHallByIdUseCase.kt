package com.graduation.domain.useCase

import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.repositories.HallRepository
import javax.inject.Inject

class GetHallByIdUseCase @Inject constructor(private val hallRepository: HallRepository) {
    suspend fun invoke(hallID: Int): HallsResponse {
        return hallRepository.getHallById(hallID)
    }
}