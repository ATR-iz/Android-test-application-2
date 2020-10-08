package com.atriztech.future_selectedjobscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.atriztech.future_selectedjobscreen.databinding.SelectedJobFragmentBinding

class SelectedJobFragment : Fragment() {
    private lateinit var viewModel: SelectedJobViewModel
    private lateinit var binding: SelectedJobFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!retainInstance ) {
            retainInstance = true

            viewModel = SelectedJobViewModel()
            binding =
                DataBindingUtil.inflate(inflater, R.layout.selected_job_fragment, container, false);
            binding.viewModel = viewModel

            viewModel.setDataFromBundle(arguments!!)

            return binding.root
        } else {
            return binding.root
        }
    }
}