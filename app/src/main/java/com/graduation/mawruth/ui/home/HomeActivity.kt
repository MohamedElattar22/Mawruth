package com.graduation.mawruth.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityHomeBinding
import com.graduation.mawruth.ui.home.viewpager.HomeViewPager
import com.graduation.mawruth.ui.home.viewpager.TestViewPagerObject
import com.graduation.mawruth.ui.login.LoginActivity
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import com.graduation.mawruth.ui.profile.ProfileActivity
import com.graduation.mawruth.ui.settings.SettingsActivity
import com.graduation.mawruth.ui.signup.SignupActivity
import com.graduation.mawruth.ui.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var adapter: HomeViewPager
    private lateinit var catAdapter: CategoriesRecyclerAdapter
    private val museumRecyclerAdapter = MuseumRecyclerAdapter(listOf())
    private lateinit var toggle: ActionBarDrawerToggle
    private var currentPage = 0
    private var timer: Timer? = null
    private val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    private val PERIOD_MS: Long = 3000
    private lateinit var viewModel: HomeViewModel
    var user: UserInformationDto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMuseumData()
        viewModel.getCategories()
        viewBinding.content.retryBtn.setOnClickListener {
            viewModel.getMuseumData()
        }
        observeLiveData()
        initDrawer()
    }


    private fun showShimmer() {
        val shimmer = Shimmer.AlphaHighlightBuilder().setAutoStart(true).setBaseAlpha(0.25f)
            .setHighlightAlpha(0.50f).setDirection(Shimmer.Direction.TOP_TO_BOTTOM).build()
        viewBinding.content.shimmer.setShimmer(shimmer)
        viewBinding.content.shimmer.startShimmer()
        viewBinding.content.shimmer.isVisible = true
        viewBinding.content.failedContent.isVisible = false
        viewBinding.content.successContent.isVisible = false
        viewBinding.viewPager.isVisible = false
        viewBinding.tabLayout.isVisible = false

    }

    private fun hideShimmer() {
        viewBinding.content.shimmer.stopShimmer()
        viewBinding.content.shimmer.isVisible = false
    }

    private fun observeLiveData() {
        viewModel.museumData.observe(this) {
            viewBinding.content.successContent.isVisible = true
            viewBinding.content.failedContent.isVisible = false
            viewBinding.viewPager.isVisible = true
            viewBinding.tabLayout.isVisible = true
            museumRecyclerAdapter.bindMuseumsList(it)
        }
        viewModel.museumCategory.observe(this) {
            catAdapter.bindMuseumsList(it)
        }

        viewModel.loadingLiveData.observe(this) {
            if (it) {
                showShimmer()
            } else {
                hideShimmer()
            }
        }
        viewModel.error.observe(this) {
            viewBinding.viewPager.isVisible = false
            viewBinding.tabLayout.isVisible = false
            viewBinding.content.successContent.isVisible = false
            viewBinding.content.failedContent.isVisible = true
            Snackbar.make(
                this,
                viewBinding.root,
                "حدث مشكلة اثناء الاتصال حاول مرة اخرى",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }


    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        adapter = HomeViewPager(TestViewPagerObject.list)
        catAdapter = CategoriesRecyclerAdapter(listOf())
        viewBinding.content.catRec.adapter = catAdapter
        viewBinding.content.museumRec.adapter = museumRecyclerAdapter
        viewBinding.viewPager.adapter = adapter
        handelTabLayoutForPager()
        initDrawer()
        museumRecyclerAdapter.onMuseumClickListener = MuseumRecyclerAdapter
            .OnMuseumClickListener { museumDto, position ->
                goToDetailsActivity()
            }

    }

    private fun handelTabLayoutForPager() {
        TabLayoutMediator(
            viewBinding.tabLayout,
            viewBinding.viewPager,
            true,
            true
        ) { tab, position ->
        }.attach()
        val handler = android.os.Handler()
        val Update = Runnable {
            if (currentPage == (TestViewPagerObject.list.size + 1) - 1) {
                currentPage = 0
            }
            viewBinding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }


    private fun initDrawer() {
        toggle =
            ActionBarDrawerToggle(
                this,
                viewBinding.drawer,
                viewBinding.toolbar,
                R.string.open,
                R.string.close,
            )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.darkBrown)
        viewBinding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        val header = viewBinding.nav.getHeaderView(0)
        val loginHeader = header.findViewById<ConstraintLayout>(R.id.loginHeader)
        val guest = header.findViewById<LinearLayout>(R.id.guestHeader)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)

        if (sharedPreferences.contains("userData")) {

            loginHeader.visibility = View.VISIBLE
            guest.visibility = View.INVISIBLE
            sharedPreferences.getString("userInfo", null)?.let {
                user = Gson().fromJson(it, UserInformationDto::class.java)
                val name = header.findViewById<TextView>(R.id.Headername)
                viewBinding.nav.menu.setGroupVisible(R.menu.drawer_menu, true)
                name.text = user?.fullName
                val email = header.findViewById<TextView>(R.id.headeremail)
                email.text = "@${user?.userName}"
                val image = header.findViewById<ImageView>(R.id.header_pic)
                Glide.with(this).load(user?.avatar).placeholder(R.drawable.person).into(image)
            }
            header.setOnClickListener {
                navigateToProfile()
            }


            viewBinding.nav.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.person -> {

                        navigateToProfile()
                    }

                    R.id.settings -> {
                        navigateToSettings()

                    }

                    R.id.logout -> {
                        handelLogOut()
                    }
                }
                return@setNavigationItemSelectedListener true

            }
        } else {
            guest.visibility = View.VISIBLE
            loginHeader.visibility = View.INVISIBLE
            viewBinding.nav.menu.clear()
            val loginBtn = header.findViewById<MaterialButton>(R.id.headerLogin)
            val registerButton = header.findViewById<MaterialButton>(R.id.headerRegister)
            loginBtn.setOnClickListener {
                navigateToLogin()
            }
            registerButton.setOnClickListener {
                navigateToRegister()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToDetailsActivity() {
        val intent = Intent(this, MuseumDetailsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToRegister() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        viewBinding.drawer.close()
    }

    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        viewBinding.drawer.close()
    }

    private fun handelLogOut() {
        viewBinding.drawer.close()
        val dialog = MaterialAlertDialogBuilder(this).create()
        val dialogView = LayoutInflater.from(this).inflate(R.layout.logout_dialog_design, null)
        dialog.setView(dialogView)
        val stay = dialogView.findViewById<MaterialButton>(R.id.stay)
        val logout = dialogView.findViewById<MaterialButton>(R.id.log)
        stay.setOnClickListener {
            dialog.dismiss()
        }
        logout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("userData")
            editor.remove("userInfo")
            editor.apply()
            navigateToSplash()
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun navigateToSplash() {
        val intent = Intent(this, SplashScreen::class.java)
        startActivity(intent)
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}