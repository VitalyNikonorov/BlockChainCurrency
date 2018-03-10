package net.nikonorov.blockchaincurrency.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.nikonorov.blockchaincurrency.data.NetworkClient
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractorImpl
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
