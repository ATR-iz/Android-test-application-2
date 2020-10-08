package com.atriztech.future_listjobsscreen.di

import android.content.Context
import com.atriztech.future_listjobsscreen.ListJobsFragment
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<App> {
    fun inject(listJobsFragment: ListJobsFragment?)

    object Initializer {
        fun init(app: App, context: Context): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(app, context))
                .build()
        }
    }
}