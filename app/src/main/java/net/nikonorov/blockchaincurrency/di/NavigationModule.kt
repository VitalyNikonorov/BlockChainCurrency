package net.nikonorov.blockchaincurrency.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
@Module
class NavigationModule {
    private val cicerone = Cicerone.create()

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
}