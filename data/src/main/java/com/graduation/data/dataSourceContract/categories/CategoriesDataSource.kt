package com.graduation.data.dataSourceContract.categories


import com.graduation.domain.model.categories.CategoriesDtoItem

interface CategoriesDataSource {
    suspend fun getCategories(): List<CategoriesDtoItem?>?

}