package com.graduation.mawruth.ui.islamicmuseum.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.databinding.FragmentIslamicMuseumFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumFavouriteFragment : Fragment() {
    private lateinit var viewBinding: FragmentIslamicMuseumFavouriteBinding
    private lateinit var viewModel: PiecesARViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentIslamicMuseumFavouriteBinding.inflate(
            inflater, container,
            false
        )
        viewModel = ViewModelProvider(this)[PiecesARViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }


}