package com.example.network.di

import com.example.network.BlogInfoNetworkDataSource
import com.example.network.retrofit.RetrofitBlogInfoNetwork
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
}