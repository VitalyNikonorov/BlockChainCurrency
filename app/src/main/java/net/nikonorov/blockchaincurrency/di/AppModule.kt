package net.nikonorov.blockchaincurrency.di

import android.content.Context
import dagger.Module
import dagger.Provides
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

}
