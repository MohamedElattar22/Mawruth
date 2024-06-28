package com.graduation.mawruth.ui.islamicmuseum.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.facebook.shimmer.Shimmer
import com.google.android.material.snackbar.Snackbar
import com.graduation.mawruth.databinding.FragmentIslamicMuseumHomeBinding
import com.graduation.mawruth.ui.halls.IslamicMuseumHallsActivity
import com.graduation.mawruth.ui.home.viewpager.HomeViewPager
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.halls.HallsAdapter
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.stories.StoriesAdapter
import com.graduation.mawruth.utils.IslamicMuseumPager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentIslamicMuseumHomeBinding
    private lateinit var viewModel: IslamicMuseumHomeFragmentViewModel
    private lateinit var hallsAdapter: HallsAdapter
    lateinit var adapter: HomeViewPager
    private lateinit var storiesAdapter: StoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentIslamicMuseumHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[IslamicMuseumHomeFragmentViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        storiesAdapter = StoriesAdapter(listOf())
        hallsAdapter = HallsAdapter(listOf())
        hallsAdapter.onStoryClickListener = HallsAdapter.OnHallListener { hallData, position ->
            val intent = Intent(requireActivity(), IslamicMuseumHallsActivity::class.java)
            intent.putExtra("hallName", hallData.name.toString())
            intent.putExtra("hallImage", hallData.imagePath.toString())
            intent.putExtra("hallDescription", hallData.description.toString())
            intent.putExtra("hallID", hallData.id.toString())
            startActivity(intent)
        }
        viewBinding.islamicContent.stories.adapter = storiesAdapter
        viewBinding.islamicContent.hallsRecycler.adapter = hallsAdapter
        adapter = HomeViewPager(IslamicMuseumPager.list)
        viewBinding.viewPager.adapter = adapter
//        TabLayoutMediator(
//
//            viewBinding.viewPager,
//            true,
//            true
//        ) { tab, position ->
//        }.attach()

        viewModel.getStories(1)
        viewModel.getHalls(6)
        subscribeToLive()
    }

    private fun subscribeToLive() {
        viewModel.storiesList.observe(viewLifecycleOwner) { storyData ->
            storiesAdapter.bindStoriesList(storyData.data!!)
        }
        viewModel.hallsList.observe(viewLifecycleOwner) { hallsData ->

            hallsAdapter.bindHallsList(hallsData.data!!)

        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showShimmer()
            } else {
                hideShimmer()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                requireContext(),
                viewBinding.root,
                "حدث مشكلة اثناء الاتصال حاول مرة اخرى",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun showShimmer() {
        val shimmer = Shimmer
            .AlphaHighlightBuilder()
            .setAutoStart(true)
            .setBaseAlpha(0.25f)
            .setHighlightAlpha(0.50f)
            .setDropoff(50f)
            .setDirection(Shimmer.Direction.TOP_TO_BOTTOM)
            .build()
        viewBinding.islamicContent.shimmer.setShimmer(shimmer)
        viewBinding.viewPager.isVisible = false

        viewBinding.islamicContent.shimmer.startShimmer()
        viewBinding.islamicContent.shimmer.isVisible = true


    }

    private fun hideShimmer() {
        viewBinding.islamicContent.shimmer.stopShimmer()
        viewBinding.viewPager.isVisible = true
        viewBinding.islamicContent.successContent.isVisible = true
        viewBinding.islamicContent.shimmer.isVisible = false
    }

}