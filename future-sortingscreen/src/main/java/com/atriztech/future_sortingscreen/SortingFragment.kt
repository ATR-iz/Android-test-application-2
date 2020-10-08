package com.atriztech.future_sortingscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atriztech.common.MainActivityApi
import com.atriztech.future_sortingscreen.databinding.SortingFragmentBinding

class SortingFragment : Fragment() {
    private lateinit var binding: SortingFragmentBinding
    private lateinit var viewModel: SortingViewModel
    private var isFragmentLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var test = this.findNavController().backStack
        if (!retainInstance ) {
            retainInstance = true

            viewModel = SortingViewModel()
            binding = DataBindingUtil.inflate(inflater, R.layout.sorting_fragment, container, false);
            binding.viewModel = viewModel
            binding.fragment = this

            return binding.root
        } else {
            return binding.root
        }
    }

    fun onSelectItemLanguage(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        viewModel.language.set(parent?.selectedItem.toString())
    }

    fun onSelectItemLocation(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        viewModel.location.set(parent?.selectedItem.toString())
    }

    fun findJobs(view: View){
        var bundle = Bundle()
        bundle.putString("location", viewModel.location.get())
        bundle.putString("language", viewModel.language.get())

        val id = (activity as MainActivityApi).actionSortingToList
        this.findNavController().navigate(id, bundle)
    }

}