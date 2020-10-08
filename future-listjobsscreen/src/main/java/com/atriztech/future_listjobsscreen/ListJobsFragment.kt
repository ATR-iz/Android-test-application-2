package com.atriztech.future_listjobsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atriztech.common.MainActivityApi
import com.atriztech.future_jobs.JobEntity
import com.atriztech.future_jobs.JobsClientImpl
import com.atriztech.future_listjobsscreen.databinding.ListJobsFragmentBinding
import com.atriztech.future_listjobsscreen.di.App
import com.atriztech.future_listjobsscreen.recyclerviewadapter.RecyclerViewJobsAdapter
import com.atriztech.future_listjobsscreen.recyclerviewadapter.RecyclerViewJobsItemDelegate
import javax.inject.Inject

class ListJobsFragment : Fragment() {
    private lateinit var binding: ListJobsFragmentBinding

    @Inject
    lateinit var viewModel: ListJobsViewModel

    @Inject
    lateinit var jobsClientImpl: JobsClientImpl

    @Inject
    lateinit var recyclerView: RecyclerViewJobsAdapter

    @Inject
    lateinit var errorDialogView: ErrorDialogView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //retainInstance служит не только флагом для сохранения фрагмента,
        //во время уничтожения макета, но и флагом для его инициализации

        if (!retainInstance ) {
            retainInstance = true

            App.component(this.requireContext())?.inject(this)
            jobsClientImpl.create()

            binding = DataBindingUtil.inflate(inflater,  R.layout.list_jobs_fragment, container, false);
            binding.viewModel = viewModel

            setDataFromBundle(arguments!!)
            setupRecyclerView()
            subscribeDataForRecyclerView()
            subscribeStatusLoading()

            viewModel.getListData()

            return binding.root
        } else{
            return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.destroyTread()
    }

    fun setDataFromBundle(bundle: Bundle){
        viewModel.language = bundle.getString("language")!!
        viewModel.location = bundle.getString("location")!!
    }

    private fun setupRecyclerView(){
        recyclerView.attachDelegate(object : RecyclerViewJobsItemDelegate {
            override fun openJob(job: JobEntity) {
                viewJob(job)
            }
        })

        binding.listItems.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.listItems.adapter = recyclerView
    }

    private fun subscribeDataForRecyclerView(){
        viewModel.listData.observe(viewLifecycleOwner, Observer { items ->
            recyclerView.setData(items)
        })
    }

    private fun subscribeStatusLoading(){
        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                is LoadState.Loading -> {
                    binding.textStatus.visibility = View.VISIBLE
                    binding.textStatus.text = "Loading..."
                }
                is LoadState.Loaded -> {
                    binding.textStatus.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    this.findNavController().popBackStack()
                    errorDialogView.showErrorDialog(layoutInflater)
                }
            }
        })
    }

    private fun viewJob(job: JobEntity){
        var bundle = Bundle()
        bundle.putString("company", job.company)
        bundle.putString("company_url", job.company_url)
        bundle.putString("title", job.title)
        bundle.putString("location", job.location)
        bundle.putString("description", job.description)

        val id = (activity as MainActivityApi).actionListToJob

        this.findNavController().navigate(id, bundle)
    }
}