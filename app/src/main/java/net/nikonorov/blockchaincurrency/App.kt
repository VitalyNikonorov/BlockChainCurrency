package net.nikonorov.blockchaincurrency

import android.app.Application
import net.nikonorov.blockchaincurrency.di.ComponentManager

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ComponentManager.initAppComponent(this.applicationContext)
    }
}