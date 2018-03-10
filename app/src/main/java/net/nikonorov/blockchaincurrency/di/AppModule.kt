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

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideCurrencyInteractor(networkClient: NetworkClient): CurrencyInteractor {
        return CurrencyInteractorImpl(networkClient)
    }
}
