package com.graduation.data.repository

import com.graduation.data.datasourceImpl.stories.StoriesDataSourceImpl
import com.graduation.domain.model.stories.StoriesResponse
import com.graduation.domain.repositories.StoriesRepository
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(private val storiesDataSourceImpl: StoriesDataSourceImpl) :
    StoriesRepository {
    override suspend fun getAllStoriesOfMuseum(museumId: Int): StoriesResponse {
        return storiesDataSourceImpl.getAllStoriesOfMuseum(museumId)
    }
}