package com.graduation.domain.useCase

import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class GetMuseumsDataUseCase
@Inject constructor(private val museumRepository: MuseumRepository) {

    suspend fun invoke(): List<MuseumDto?>? {
        val result = museumRepository.getMuseum()
        return result
    }
}