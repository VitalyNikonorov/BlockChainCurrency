package net.nikonorov.blockchaincurrency.di

import android.content.Context
import net.nikonorov.blockchaincurrency.di.main.MainScreenComponent
import net.nikonorov.blockchaincurrency.di.main.MainScreenModule

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

object ComponentManager {

    private var appComponent: AppComponent? = null
    private var mainScreenComponent: MainScreenComponent? = null

    fun initAppComponent(context: Context) {
        appComponent = buildComponent(context)
    }

    private fun buildComponent(context: Context): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }

    fun getMainScreenComponent(): MainScreenComponent? {
        if (mainScreenComponent == null) {
            mainScreenComponent = appComponent?.plus(MainScreenModule())
        }
        return mainScreenComponent
    }

}
