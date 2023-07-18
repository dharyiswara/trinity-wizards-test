package com.trinitywizards.test.daryhilmyiswara.network

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

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