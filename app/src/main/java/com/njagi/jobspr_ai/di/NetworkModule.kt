package com.njagi.jobspr_ai.di

import com.njagi.jobspr_ai.core.CONSTANTS
import com.njagi.jobspr_ai.core.OpenAIRequestInterceptor
import com.njagi.jobspr_ai.network.Jobapi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun okHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(OpenAIRequestInterceptor())
            .build()
    }


    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CONSTANTS.BASE_URL)
            .build()
    }

    @Provides
    fun apiService(retrofit: Retrofit): Jobapi {
        return retrofit.create(Jobapi::class.java)

    }
}