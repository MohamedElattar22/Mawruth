package com.graduation.data.repository

import com.graduation.data.datasourceImpl.collections.CollectionsDataSourceImpl
import com.graduation.domain.model.collection.CollectionsResponse
import com.graduation.domain.repositories.CollectionsRepository
import javax.inject.Inject

class CollectionsRepositoryImpl @Inject constructor(private val collectionsDataSourceImpl: CollectionsDataSourceImpl) :
    CollectionsRepository {
    override suspend fun getCollectionsOfMuseum(museumID: Int): CollectionsResponse? {
        return collectionsDataSourceImpl.getCollectionsOfMuseum(museumID)
    }

}