package com.graduation.mawruth.ui.arActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.graduation.mawruth.databinding.FragmentObjectDataBinding


class ObjectDataFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentObjectDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentObjectDataBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        val itemName = requireActivity().intent.getStringExtra("pieceName").toString()
        val itemDes = requireActivity().intent.getStringExtra("pieceDes").toString()
        viewBinding.pieceTitle.text = itemName
        viewBinding.description.text = itemDes
    }


}