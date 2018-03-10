package net.nikonorov.blockchaincurrency.di

import dagger.Module
import dagger.Provides
import net.nikonorov.blockchaincurrency.App
import net.nikonorov.blockchaincurrency.BuildConfig
import net.nikonorov.blockchaincurrency.data.NetworkClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (App.DEBUG_NETWORK && BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideNetworkClient(builder: Retrofit.Builder): NetworkClient {
        return NetworkClient(builder)
    }
}