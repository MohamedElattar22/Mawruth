package com.graduation.domain.useCase

import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class GetMuseumByIdUseCase @Inject constructor
    (private val museumRepository: MuseumRepository) {

    suspend fun invoke(museumId: Int): MuseumItem? {
        return museumRepository.getMuseumById(museumId)
    }
}