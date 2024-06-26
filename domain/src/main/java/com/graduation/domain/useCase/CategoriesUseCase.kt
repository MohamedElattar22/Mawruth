package com.graduation.domain.useCase

import com.graduation.domain.model.categories.CategoriesResponse
import com.graduation.domain.repositories.CategoriesRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend fun invoke(): CategoriesResponse? {
        return categoriesRepository.getCategories()
    }
}