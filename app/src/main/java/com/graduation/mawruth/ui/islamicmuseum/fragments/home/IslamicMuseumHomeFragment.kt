package com.graduation.mawruth.ui.islamicmuseum.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.collections.CollectionPiecesActivity
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.collections.CollectionsAdapter
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.halls.HallsAdapter
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.stories.StoriesAdapter
import com.graduation.mawruth.ui.stories.StoriesActivity
import com.graduation.mawruth.utils.IslamicMuseumPager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentIslamicMuseumHomeBinding
    private lateinit var viewModel: IslamicMuseumHomeFragmentViewModel
    private lateinit var hallsAdapter: HallsAdapter
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var collectionsAdapter: CollectionsAdapter
    lateinit var adapter: HomeViewPager

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
        collectionsAdapter = CollectionsAdapter(listOf())
        hallsAdapter.onStoryClickListener = HallsAdapter.OnHallListener { hallData, position ->
            val intent = Intent(requireActivity(), IslamicMuseumHallsActivity::class.java)
            intent.putExtra("hallName", hallData.name.toString())
            intent.putExtra("hallImage", hallData.imagePath.toString())
            intent.putExtra("soundPath", hallData.soundPath.toString())
            intent.putExtra("soundImage", hallData.soundImage.toString())
            intent.putExtra("hallDescription", hallData.description.toString())
            intent.putExtra("hallID", hallData.id.toString())
            startActivity(intent)
        }
        viewBinding.islamicContent.stories.adapter = storiesAdapter
        viewBinding.islamicContent.hallsRecycler.adapter = hallsAdapter
        viewBinding.islamicContent.collectionsRV.adapter = collectionsAdapter
        adapter = HomeViewPager(IslamicMuseumPager.list)
        viewBinding.viewPager.adapter = adapter
//        TabLayoutMediator(
//
//            viewBinding.viewPager,
//            true,
//            true
//        ) { tab, position ->
//        }.attach()

        viewModel.getStories(49)
        viewModel.getHalls(49)
        viewModel.getCollections(49)
        subscribeToLive()
        settingStoriesClick()
        setCollectionsOnCLick()
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
        viewModel.collectionsList.observe(viewLifecycleOwner) {
            Log.d("collectionsAdapter", it.toString())
            collectionsAdapter.bindHallsList(it?.data!!)
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

    private fun settingStoriesClick() {
        storiesAdapter.onStoryClickListener =
            StoriesAdapter.OnStoryClickListener { storyData, position ->
                val intent = Intent(requireActivity(), StoriesActivity::class.java)
                intent.putExtra("storyName", storyData.name.toString())
                intent.putExtra("storyImage", storyData.image.toString())
                intent.putExtra("storyContent", storyData.content.toString())
                startActivity(intent)

            }
    }

    private fun setCollectionsOnCLick() {
        collectionsAdapter.onCollectionClickListener =
            CollectionsAdapter.OnCollectionListener { collectionData, position ->
                val intent = Intent(requireActivity(), CollectionPiecesActivity::class.java)
                intent.putExtra("collectionsName", collectionData.name.toString())
                intent.putExtra("collectionID", collectionData.id.toString())
                intent.putExtra("museumID", collectionData.museumId.toString())
                startActivity(intent)

            }
    }
}