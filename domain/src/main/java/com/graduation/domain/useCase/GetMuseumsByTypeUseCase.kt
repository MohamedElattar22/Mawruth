package com.graduation.domain.useCase

import com.graduation.domain.model.museumdata.MuseumDataDto
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class GetMuseumsByTypeUseCase @Inject
constructor(private val museumRepository: MuseumRepository) {
    suspend fun invoke(typeId: Int): List<MuseumDataDto?> {
        return museumRepository.getMuseumsByType(typeId)
    }

}