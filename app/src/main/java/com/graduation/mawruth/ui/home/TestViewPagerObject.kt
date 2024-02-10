package com.graduation.mawruth.ui.home

import com.graduation.mawruth.R

data class TestViewPagerObject(
    val title: String,
    val description: String,
    val image: Int
) {
    companion object {
        fun getList(): MutableList<TestViewPagerObject> {
            return mutableListOf(
                TestViewPagerObject(
                    "تعامــد الشمــس !",
                    "ترقــب الحـــدث الأكبر فــي\n" + "معبــد الكرنــك اليوم !",
                    R.drawable.vp_image1
                ),
                TestViewPagerObject(
                    "تعامــد الشمــس !",
                    "ترقــب الحـــدث الأكبر فــي\n" + "معبــد الكرنــك اليوم !",
                    R.drawable.vp_image2
                ),
                TestViewPagerObject(
                    "تعامــد الشمــس !",
                    "ترقــب الحـــدث الأكبر فــي\n" + "معبــد الكرنــك اليوم !",
                    R.drawable.vp_image3
                ),
                TestViewPagerObject(
                    "تعامــد الشمــس !",
                    "ترقــب الحـــدث الأكبر فــي\n" + "معبــد الكرنــك اليوم !",
                    R.drawable.vp_image4
                )
            )
        }
    }
}
