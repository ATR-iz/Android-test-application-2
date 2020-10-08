package com.atriztech.future_listjobsscreen

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atriztech.common.scheduler.BaseSchedulerProvider
import com.atriztech.future_jobs.JobEntity
import com.atriztech.future_jobs.JobsClient
import io.reactivex.observers.DisposableObserver
import org.jsoup.Jsoup

class ListJobsViewModel constructor(private val schedulerProvider: BaseSchedulerProvider, private var jobsClient: JobsClient) : ViewModel() {
    var listData = MutableLiveData<List<JobEntity>>()
    var status = MutableLiveData<LoadState>()

    var language: String = ""
    var location: String = ""

    private lateinit var disposableObserver: DisposableObserver<List<JobEntity>>

    fun getListData() {
        disposableObserver = object : DisposableObserver<List<JobEntity>>() {
            override fun onStart() {
                status.postValue(LoadState.Loading)
            }

            override fun onNext(list: List<JobEntity>) {
                listData.postValue(clearText(list))
                status.postValue(LoadState.Loaded)
            }

            override fun onError(e: Throwable) {
                listData.postValue(listOf())
                status.postValue(LoadState.Error)
            }

            override fun onComplete() {}
        }

        jobsClient.jobsApi.getJobs(language, location)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(disposableObserver)
    }

    fun destroyTread(): Boolean{
        disposableObserver.dispose()
        return  disposableObserver.isDisposed
    }

    fun clearText(list: List<JobEntity>): List<JobEntity>{
        for (item in list){
            item.description = Jsoup.parse(item.description).text()
        }

        return list
    }

    fun setDataFromBundle(bundle: Bundle){
        language = bundle.getString("language")!!
        location = bundle.getString("location")!!
    }
}