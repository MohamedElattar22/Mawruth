package com.graduation.data.repository

import com.graduation.data.datasourceImpl.categories.CategoriesDataSourceImpl
import com.graduation.domain.model.categories.CategoriesDtoItem
import com.graduation.domain.repositories.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl
@Inject constructor(private val categoriesDataSourceImpl: CategoriesDataSourceImpl) :
    CategoriesRepository {
    override suspend fun getCategories(): List<CategoriesDtoItem?>? {
        return categoriesDataSourceImpl.getCategories()
    }

}