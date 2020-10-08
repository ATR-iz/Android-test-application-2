package com.atriztech.core_network.network_utils

import android.app.Application
import android.content.Context
import com.atriztech.core_network.network_utils.NetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class NetworkCacheInterceptor @Inject constructor(var context: Context) : Interceptor {
    private val MAX_AGE = 120
    private val MAX_STALE: Long = 86400

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val cacheHeaderValue =
            if (NetworkAvailable.isNetworkAvailable(context)) "public, max-age=$MAX_AGE" else "public, only-if-cached, max-stale=$MAX_STALE"
        val request: Request = originalRequest.newBuilder().build()
        val response: Response = chain.proceed(request)
        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheHeaderValue)
            .build()
    }
}