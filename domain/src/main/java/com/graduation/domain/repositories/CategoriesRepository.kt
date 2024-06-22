package com.graduation.domain.repositories

import com.graduation.domain.model.categories.CategoriesResponse


interface CategoriesRepository {
    suspend fun getCategories(): CategoriesResponse?
}