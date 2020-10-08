package com.atriztech.future_listjobsscreen.di

import android.app.Application
import android.content.Context
import com.atriztech.common.scheduler.SchedulerProvider
import com.atriztech.future_jobs.JobsClientImpl
import com.atriztech.future_listjobsscreen.ListJobsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App, private val context: Context) {

    @Provides
    @Singleton
    protected fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    protected fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    protected fun provideListJobsViewModel(schedulerProvider: SchedulerProvider, jobsClientImpl: JobsClientImpl): ListJobsViewModel {
        return ListJobsViewModel(schedulerProvider, jobsClientImpl)
    }
}