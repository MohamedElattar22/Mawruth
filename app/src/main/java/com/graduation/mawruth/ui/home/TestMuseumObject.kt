package com.graduation.mawruth.ui.home

import com.graduation.mawruth.R

data class TestMuseumObject(
    val museumName: String,
    val museumLocation: String,
    val museumRate: String,
    val image: Int,
    val chip1: String,
    val chip2: String,
    val chip3: String,
) {
    companion object {
        fun getList(): MutableList<TestMuseumObject> {
            return mutableListOf(
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                ),
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                ),
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                ),
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                ),
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                ),
                TestMuseumObject(
                    "المتحف المصري",
                    "القاهرة - مصر",
                    "4.9 ",
                    R.drawable.museum_pic,
                    "تاريخي",
                    "مصري",
                    "فرعوني"
                )
            )
        }
    }
}
