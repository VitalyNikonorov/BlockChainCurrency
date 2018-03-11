package net.nikonorov.blockchaincurrency

import android.app.Application
import net.nikonorov.blockchaincurrency.di.ComponentManager
import net.nikonorov.blockchaincurrency.utils.LogDebugTree
import timber.log.Timber


/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class App : Application() {
    companion object {
        val DEBUG_NETWORK = true
        val DEBUG_LOG = true
    }

    override fun onCreate() {
        super.onCreate()
        ComponentManager.initAppComponent(this.applicationContext)
        if (BuildConfig.DEBUG && DEBUG_LOG) {
            Timber.plant(LogDebugTree())
        }
    }
}
