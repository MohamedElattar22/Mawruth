package com.graduation.domain.useCase

import com.graduation.domain.model.stories.StoriesResponse
import com.graduation.domain.repositories.StoriesRepository
import javax.inject.Inject

class GetAllStoriesOfMuseumUseCase @Inject constructor
    (private val storiesRepository: StoriesRepository) {
    suspend fun invoke(museumId: Int): StoriesResponse {
        return storiesRepository.getAllStoriesOfMuseum(museumId)!!

    }


}