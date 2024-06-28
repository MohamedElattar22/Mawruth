package com.graduation.data.datasourceImpl.stories

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.stories.StoriesDataSource
import com.graduation.domain.model.stories.StoriesResponse
import javax.inject.Inject

class StoriesDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    StoriesDataSource {
    override suspend fun getAllStoriesOfMuseum(museumId: Int): StoriesResponse {
        return webServices.getStoriesOfMuseum(museumId).toStoryResponse()
    }

}