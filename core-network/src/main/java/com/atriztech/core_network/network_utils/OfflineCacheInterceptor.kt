package com.atriztech.core_network.network_utils

import android.app.Application
import android.content.Context
import com.atriztech.core_network.network_utils.NetworkAvailable
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.jvm.Throws

class OfflineCacheInterceptor @Inject constructor(var context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (NetworkAvailable.isNetworkAvailable(context)) {
            val cacheControl = CacheControl.Builder()
                .maxStale(1, TimeUnit.DAYS)
                .build()
            request = request.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
        return chain.proceed(request)
    }
}