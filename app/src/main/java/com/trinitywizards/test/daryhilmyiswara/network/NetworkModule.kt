package com.trinitywizards.test.daryhilmyiswara.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val gson by lazy {
        Gson()
    }

    @Provides
    fun provideGson(): Gson {
        return gson
    }
}