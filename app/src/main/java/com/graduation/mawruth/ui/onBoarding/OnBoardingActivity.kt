package com.graduation.mawruth.ui.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityOnboardingBinding
import com.graduation.mawruth.ui.getstarted.GetStartedActivity
import com.graduation.mawruth.ui.onBoarding.adapter.ViewPagerAdapter
import com.graduation.mawruth.ui.onBoarding.adapter.onNextBtnClick

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityOnboardingBinding
    private lateinit var adapter : ViewPagerAdapter
    private var imagesList = mutableListOf<Int>()
    private var headerList = mutableListOf<String>()
    private var contentList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
        contentCreate()
        applySkipBtn()
    }
    fun contentCreate(){

        imagesList.add(R.drawable.ar_onboard)
        imagesList.add(R.drawable.ai_onboarding)
        imagesList.add(R.drawable.maps_onboarding)
        headerList.add("تجربة بصرية فريدة !")
        headerList.add("معزز بالذكاء الاصطناعي !")
        headerList.add("خرائط داخلية للمتاحف !")
        contentList.add("بإستخــدام تقنيــة الواقــع المعــزز , يمكنك \n" +
                "الآن الحصول علــي تجــربة بصـــرية فريـــدة \n" +
                "خلال زيارتك للمتحف")
        contentList.add("موروث يستخدم الذكاء الإصطناعي , للتعرف \n" +
                "علي التحف الأثرية و التماثيل")
        contentList.add("تعرف علي مواقع و أماكن التحف الأثرية\n" +
                "بالقرب منك داخل المتجف")

        adapter = ViewPagerAdapter(imagesList , headerList ,contentList)
        viewBinding.viewPager.adapter = adapter

        viewBinding.nextBtn.setOnClickListener {
            viewBinding.viewPager.apply {
                beginFakeDrag()
                fakeDragBy(-10f)
                endFakeDrag()
            }
        }

        viewBinding.circleIndicator.setViewPager(viewBinding.viewPager)
        adapter.onClick = object:onNextBtnClick{
            override fun onBtnClick() {
                val intent = Intent(
                    this@OnBoardingActivity,
                    GetStartedActivity::class.java
                )
                startActivity(intent)
                finish()
            }
        }

    }
    private fun navigateToGetStarted() {
        val getStarted = Intent(
            this,
            GetStartedActivity::class.java
        )
        startActivity(getStarted)
        finish()
    }

    override fun onStart() {
        super.onStart()
        checkToDisplayOnBoarding()
    }

    private fun checkToDisplayOnBoarding(){
        val shared = getSharedPreferences("isOpened" , MODE_PRIVATE)
        val data = shared.getString("isOpened", "")
        if(data == ""){
            val editor =   shared.edit()
            editor.putString("isOpened" , "opened")
            editor.apply()
        }
        else{
            navigateToGetStarted()
        }
    }
    private fun applySkipBtn(){
        viewBinding.skipToGetStartedBtn.setOnClickListener {
            navigateToGetStarted()
        }
    }
}