package com.graduation.data.datasourceImpl.categories

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.categories.CategoriesDataSource
import com.graduation.domain.model.categories.CategoriesDtoItem

import javax.inject.Inject

class CategoriesDataSourceImpl @Inject constructor(val webServices: WebServices) :
    CategoriesDataSource {
    override suspend fun getCategories(): List<CategoriesDtoItem?>? {
        val categories = webServices.getCategories()
        return categories?.map {
            it?.toCategoryDTO()
        }
    }

}