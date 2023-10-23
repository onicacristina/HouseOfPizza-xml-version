package com.example.myapplication.di

import com.example.myapplication.data.remote.RestaurantApiService
import com.example.myapplication.data.repository.RestaurantRepositoryImpl
import com.example.myapplication.di.interceptor.DefaultHeaderInterceptor
import com.example.myapplication.domain.repository.RestaurantRepository
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(
        defaultHeaderInterceptor: DefaultHeaderInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(defaultHeaderInterceptor)
            .addInterceptor(loggingInterceptor)
            .pingInterval(3, TimeUnit.SECONDS)
            .callTimeout(100, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .protocols(listOf(Protocol.HTTP_1_1))
            .connectionPool(ConnectionPool(0, 2, TimeUnit.MINUTES))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .callFactory(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideRestaurantApiService(retrofit: Retrofit): RestaurantApiService {
        return retrofit.create(RestaurantApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRestaurantApiInterface(animalsApiInterface: RestaurantRepositoryImpl): RestaurantRepository {
        return animalsApiInterface
    }

}