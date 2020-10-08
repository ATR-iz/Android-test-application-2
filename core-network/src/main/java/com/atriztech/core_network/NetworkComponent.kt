package com.atriztech.core_network

import com.atriztech.common.ContextModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ContextModule::class])
@Singleton
interface NetworkComponent {
    fun retrofit(): Retrofit
}