package com.graduation.mawruth.ui.home

import com.graduation.mawruth.R

class TestCategoriesObject(
    val image: Int,
    val name: String
) {
    companion object {
        fun getList(): MutableList<TestCategoriesObject> {
            return mutableListOf(
                TestCategoriesObject(
                    R.drawable.cat1,
                    "فنون"
                ),
                TestCategoriesObject(
                    R.drawable.cat2,
                    "اسلامي"
                ),
                TestCategoriesObject(
                    R.drawable.cat3,
                    "تاريخي"
                ),
                TestCategoriesObject(
                    R.drawable.cat4,
                    "مصري قديم"
                )
            )
        }
    }
}