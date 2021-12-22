package com.boilerplate.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.boilerplate.demo.base.ui.BaseFragment
import com.boilerplate.demo.databinding.FragmentDetailLayoutBinding
import com.boilerplate.demo.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: AppViewModel by activityViewModels()
    lateinit var binding: FragmentDetailLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.itemResponse = arguments?.getInt(ITEM_POSITION)?.let {
            viewModel.list.value?.data?.get(
                it
            )
        }
    }


    companion object {
        private const val ITEM_POSITION = "item_position"

        @JvmStatic
        fun newInstance(itemPosition: Int): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ITEM_POSITION, itemPosition)
                }
            }
        }
    }
}