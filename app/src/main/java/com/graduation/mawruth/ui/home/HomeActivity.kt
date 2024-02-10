package com.graduation.mawruth.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityHomeBinding
import com.graduation.mawruth.ui.profile.ProfileActivity
import com.graduation.mawruth.ui.settings.SettingsActivity
import java.util.Timer
import java.util.TimerTask


class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    lateinit var adapter: HomeViewPager
    lateinit var catAdapter: CategoriesRecyclerAdapter
    private lateinit var museumRecyclerAdapter: MuseumRecyclerAdapter
    private lateinit var toggle: ActionBarDrawerToggle
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed

    val PERIOD_MS: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        adapter = HomeViewPager(TestViewPagerObject.getList())
        catAdapter = CategoriesRecyclerAdapter(TestCategoriesObject.getList())
        museumRecyclerAdapter = MuseumRecyclerAdapter(TestMuseumObject.getList())
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
//            val header = viewBinding.nav.getHeaderView(0)
//            val name = header.findViewById<TextView>(R.id.name)
//            name.text = SessionProvider.user?.email.toString()

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
            }
            return@setNavigationItemSelectedListener true

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}