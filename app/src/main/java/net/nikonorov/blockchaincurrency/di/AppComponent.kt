package net.nikonorov.blockchaincurrency.di

import dagger.Component
import net.nikonorov.blockchaincurrency.di.main.MainScreenComponent
import net.nikonorov.blockchaincurrency.di.main.MainScreenModule
import net.nikonorov.blockchaincurrency.di.pairinfo.PairInfoModule
import net.nikonorov.blockchaincurrency.di.pairinfo.PairInfoScreenComponent
import net.nikonorov.blockchaincurrency.presentation.MainActivity
import javax.inject.Singleton

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plus(mainScreenModule: MainScreenModule) : MainScreenComponent

    fun plus(pairInfoModule: PairInfoModule) : PairInfoScreenComponent

    fun inject(mainActivity: MainActivity)

}
