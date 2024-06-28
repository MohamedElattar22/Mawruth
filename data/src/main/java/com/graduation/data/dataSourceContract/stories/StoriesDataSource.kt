package com.graduation.data.dataSourceContract.stories

import com.graduation.domain.model.stories.StoriesResponse

interface StoriesDataSource {
    suspend fun getAllStoriesOfMuseum(
        museumId: Int
    ): StoriesResponse?

}