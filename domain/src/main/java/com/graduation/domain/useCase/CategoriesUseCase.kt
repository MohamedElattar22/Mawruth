package com.graduation.domain.useCase

import com.graduation.domain.model.categories.CategoriesDtoItem
import com.graduation.domain.repositories.CategoriesRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend fun invoke(): List<CategoriesDtoItem?>? {
        return categoriesRepository.getCategories()
    }
}