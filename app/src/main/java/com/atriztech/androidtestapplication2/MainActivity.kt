package com.atriztech.androidtestapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atriztech.common.MainActivityApi

class MainActivity: AppCompatActivity(), MainActivityApi {
    override val actionSortingToList: Int = R.id.action_sortingJobsFragment_to_listJobsFragment
    override val actionListToJob: Int = R.id.action_listJobsFragment_to_selectedJobFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}