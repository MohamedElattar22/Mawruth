package com.graduation.domain.repositories

import com.graduation.domain.model.collection.CollectionsResponse

interface CollectionsRepository {
    suspend fun getCollectionsOfMuseum(museumID: Int): CollectionsResponse?
}