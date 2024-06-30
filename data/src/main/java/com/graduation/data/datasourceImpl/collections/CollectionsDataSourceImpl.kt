package com.graduation.data.datasourceImpl.collections

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.collections.CollectionsDataSource
import com.graduation.domain.model.collection.CollectionsResponse
import javax.inject.Inject

class CollectionsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    CollectionsDataSource {
    override suspend fun getCollectionsOfMuseum(museumID: Int): CollectionsResponse {
        return webServices.getCollectionOfMuseum(museumID).toCollectionsResponse()
    }
}