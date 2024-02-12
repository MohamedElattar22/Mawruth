package com.graduation.mawruth.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.facebook.shimmer.Shimmer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityHomeBinding
import com.graduation.mawruth.ui.home.viewpager.HomeViewPager
import com.graduation.mawruth.ui.home.viewpager.TestViewPagerObject
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import com.graduation.mawruth.ui.profile.ProfileActivity
import com.graduation.mawruth.ui.settings.SettingsActivity
import com.graduation.mawruth.ui.splash.SplashScreen
import com.graduation.mawruth.utils.SessionProvider
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
        observeLiveData()
    }

    private fun showShimmer() {
        val shimmer = Shimmer.AlphaHighlightBuilder().setAutoStart(true).setBaseAlpha(0.25f)
            .setHighlightAlpha(0.75f).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).build()
        viewBinding.content.museumRec.isVisible = false
        viewBinding.content.shimmer.setShimmer(shimmer)
        viewBinding.content.shimmer.startShimmer()
        viewBinding.content.shimmer.isVisible = true
    }

    private fun hideShimmer() {
        viewBinding.content.museumRec.isVisible = true
        viewBinding.content.shimmer.stopShimmer()
        viewBinding.content.shimmer.isVisible = false

    }

    private fun observeLiveData() {
        viewModel.museumData.observe(this) {
            museumRecyclerAdapter.bindMuseumsList(it)
        }
        viewModel.loadingLiveData.observe(this) {
            if (it) {
                showShimmer()
            } else {
                hideShimmer()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMuseumData()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        WindowCompat.setDecorFitsSystemWindows(window, false)
        adapter = HomeViewPager(TestViewPagerObject.getList())
        catAdapter = CategoriesRecyclerAdapter(TestCategoriesObject.getList())
        viewBinding.content.catRec.adapter = catAdapter
        viewBinding.content.museumRec.adapter = museumRecyclerAdapter
        viewBinding.viewPager.adapter = adapter
        TabLayoutMediator(
            viewBinding.tabLayout,
            viewBinding.viewPager,
            true,
            true
        ) { tab, position ->
        }.attach()
        val handler = android.os.Handler()
        val Update = Runnable {
            if (currentPage === (TestViewPagerObject.getList().size + 1) - 1) {
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
        initDrawer()
        museumRecyclerAdapter.onMuseumClickListener = MuseumRecyclerAdapter
            .OnMuseumClickListener { museumDto, position ->
                goToDetailsActivity()
            }

    }

    private fun goToDetailsActivity() {
        val intent = Intent(this, MuseumDetailsActivity::class.java)
        startActivity(intent)
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
        val name = header.findViewById<TextView>(R.id.Headername)
        name.text = SessionProvider.user?.userName.toString()
        val email = header.findViewById<TextView>(R.id.headeremail)
        email.text = SessionProvider.user?.email
        viewBinding.nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.person -> {

                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    viewBinding.drawer.close()
                }

                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    viewBinding.drawer.close()

                }

                R.id.logout -> {
                    handelLogOut()
                }
            }
            return@setNavigationItemSelectedListener true

        }


    }

    private fun handelLogOut() {
        viewBinding.drawer.close()
        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setTitle("تسجيل الخروج")
        dialog.setIcon(R.drawable.mylogo)
        dialog.setMessage("هل حقا تريد مغادرة تارثك ؟")
        dialog.setPositiveButton(
            "نعم"
        ) { dialog, which ->
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("userData")
            editor.apply()
            SessionProvider.user = null
            navigateToSplash()
            dialog.dismiss()
        }
        dialog.setNegativeButton("لا") { dialog, _ ->
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