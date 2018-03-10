package net.nikonorov.blockchaincurrency.di.main

import dagger.Subcomponent
import net.nikonorov.blockchaincurrency.presentation.main.view.MainFragment

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
@Subcomponent(modules = arrayOf(MainScreenModule::class))
@MainScreenScope
interface MainScreenComponent {
    fun inject(mainFragment: MainFragment)
}
