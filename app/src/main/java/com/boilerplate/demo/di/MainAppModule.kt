package com.boilerplate.demo.di

import com.boilerplate.demo.service.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
object MainAppModule {


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder, client: OkHttpClient): ApiServices {
        return retrofit
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }

}