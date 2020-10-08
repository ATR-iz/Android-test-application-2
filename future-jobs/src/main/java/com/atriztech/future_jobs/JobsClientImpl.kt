package com.atriztech.future_jobs

import android.content.Context
import com.atriztech.common.ContextModule
import com.atriztech.core_network.DaggerNetworkComponent
import com.atriztech.core_network.NetworkModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsClientImpl @Inject constructor(var context: Context): JobsClient {
    override lateinit var jobsApi: JobsApi

    //Я бы вот здесь сделал Init, чтобы при создании экземпляра, сразу создавалась АПИ.
    //Но, тогда начнуться проблемы с тестами.
     fun create() {
        val daggerNetworkComponent = DaggerNetworkComponent.builder()
            .contextModule(ContextModule(context))
            .networkModule(NetworkModule("https://jobs.github.com/"))
            .build()

        jobsApi = daggerNetworkComponent.retrofit().create<JobsApi>(JobsApi::class.java)
    }
}