package com.graduation.mawruth.ui.islamicmuseum.fragments.discover

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.databinding.FragmentIslamicMuseumDiscoverBinding
import com.graduation.mawruth.ui.halls.IslamicMuseumHallsActivity
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.halls.HallsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumDiscoverFragment : Fragment() {
    private lateinit var viewBinding: FragmentIslamicMuseumDiscoverBinding
    private lateinit var viewModel: IslamicMuseumDiscoverViewModel
    private lateinit var hallsAdapter: HallsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentIslamicMuseumDiscoverBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[IslamicMuseumDiscoverViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        viewModel.getHallsOfMuseum(49)
        setAdapter()
        subscribeToLiveData()
    }

    private fun setAdapter() {
        hallsAdapter = HallsAdapter(listOf())
        viewBinding.hallsRV.adapter = hallsAdapter

    }

    private fun subscribeToLiveData() {
        viewModel.hallList.observe(viewLifecycleOwner) {
            hallsAdapter.bindHallsList(it?.data!!)
            hallsAdapter.onStoryClickListener = HallsAdapter.OnHallListener { hallData, position ->
                val intent = Intent(requireActivity(), IslamicMuseumHallsActivity::class.java)
                intent.putExtra("hallName", hallData.name.toString())
                intent.putExtra("hallID", hallData.id.toString())
                intent.putExtra("hallImage", hallData.imagePath.toString())
                intent.putExtra("hallDescription", hallData.description.toString())
                intent.putExtra("soundImage", hallData.soundImage.toString())
                intent.putExtra("soundPath", hallData.soundPath.toString())
                startActivity(intent)
            }
        }
    }

}