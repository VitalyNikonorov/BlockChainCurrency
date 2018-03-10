package net.nikonorov.blockchaincurrency.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.nikonorov.blockchaincurrency.App
import net.nikonorov.blockchaincurrency.BuildConfig
import net.nikonorov.blockchaincurrency.data.NetworkClient
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton


/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
@Module
class AppModule(private val context: Context) {

    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

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

    @Provides
    @Singleton
    fun provideCurrencyInteractor(networkClient: NetworkClient): CurrencyInteractor {
        return CurrencyInteractorImpl(networkClient)
    }
}
