package com.atriztech.future_jobs

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {
    //positions.json?description=python&location=new+york
    @GET("positions.json?")
    fun getJobs(@Query("description") language: String, @Query("location") location: String): Observable<List<JobEntity>>
}