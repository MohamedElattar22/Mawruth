package com.graduation.data.dataSourceContract.categories


import com.graduation.domain.model.categories.CategoriesResponse

interface CategoriesDataSource {
    suspend fun getCategories(): CategoriesResponse?

}