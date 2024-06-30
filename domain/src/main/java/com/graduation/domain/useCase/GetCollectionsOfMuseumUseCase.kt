package com.graduation.domain.useCase

import com.graduation.domain.model.collection.CollectionsResponse
import com.graduation.domain.repositories.CollectionsRepository
import javax.inject.Inject

class GetCollectionsOfMuseumUseCase @Inject constructor(private val collectionsRepository: CollectionsRepository) {
    suspend fun invoke(museumID: Int): CollectionsResponse? {
        return collectionsRepository.getCollectionsOfMuseum(museumID)
    }

}