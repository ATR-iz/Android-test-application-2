package com.atriztech.future_listjobsscreen.di

import android.app.Application
import android.content.Context
import com.atriztech.future_listjobsscreen.di.AppComponent.Initializer.init

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
        buildComponentGraph()
    }

    companion object {
        private var appComponent: AppComponent? = null
        private var app: App? = null
        private var context: Context? = null

        fun component(context: Context): AppComponent? {
            this.context = context

            App().onCreate()

            return appComponent
        }

        fun buildComponentGraph() {
            appComponent = init(app!!, context!!)
        }
    }
}