package com.graduation.domain.repositories

import com.graduation.domain.model.stories.StoriesResponse

interface StoriesRepository {
    suspend fun getAllStoriesOfMuseum(
        museumId: Int
    ): StoriesResponse?
}