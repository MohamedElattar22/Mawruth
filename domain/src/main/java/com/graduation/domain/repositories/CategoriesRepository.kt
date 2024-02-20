package com.graduation.domain.repositories


import com.graduation.domain.model.categories.CategoriesDtoItem

interface CategoriesRepository {
    suspend fun getCategories(): List<CategoriesDtoItem?>?
}