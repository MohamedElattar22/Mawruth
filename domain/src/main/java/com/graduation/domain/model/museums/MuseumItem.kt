package com.graduation.domain.model.museums

data class MuseumItem(

    val images: List<MuseumImagesItem?>? = null,

    val city: String? = null,

    val street: String? = null,

    val name: String? = null,

    val description: String? = null,

    val id: Int? = null,

    val categories: List<MuseumCategoriesItem?>? = null,

    var isFavourite: Boolean?=null
)