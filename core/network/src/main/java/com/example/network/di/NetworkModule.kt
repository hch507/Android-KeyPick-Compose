package com.example.network.di

import com.example.network.BlogInfoNetworkDataSource
import com.example.network.NaverNetworkDataSource
import com.example.network.NaverRelNetworkDataSource
import com.example.network.retrofit.RetrofitBlogInfoNetwork
import com.example.network.retrofit.RetrofitNaverNetwork
import com.example.network.retrofit.RetrofitNaverRelNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindBlogInfoNetworkDataSource(
        impl: RetrofitBlogInfoNetwork
    ): BlogInfoNetworkDataSource

    @Binds
    abstract fun bindNaverNetworkDataSource(
        impl: RetrofitNaverNetwork
    ): NaverNetworkDataSource

    @Binds
    abstract fun bindNaverRelNetworkDataSource(
        impl: RetrofitNaverRelNetwork
    ): NaverRelNetworkDataSource
}