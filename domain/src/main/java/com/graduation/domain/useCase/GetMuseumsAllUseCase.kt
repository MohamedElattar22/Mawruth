package com.graduation.domain.useCase

import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.repositories.MuseumRepository
import javax.inject.Inject

class GetMuseumsAllUseCase
@Inject constructor(private val museumRepository: MuseumRepository) {
    suspend fun invoke(
        page: Int? = null,
        limit: Int? = null,
        name: String? = null,
        category: String? = null,
        city: String? = null
        ,userId:Int?=null
    ): MuseumsResponse? {
        val result = museumRepository.getAllMuseum(page, limit, name, category, city,userId)
        return result
    }
}