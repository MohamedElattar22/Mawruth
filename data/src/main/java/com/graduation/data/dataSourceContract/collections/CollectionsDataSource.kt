package com.graduation.data.dataSourceContract.collections

import com.graduation.domain.model.collection.CollectionsResponse

interface CollectionsDataSource {
    suspend fun getCollectionsOfMuseum(museumID: Int): CollectionsResponse?

}