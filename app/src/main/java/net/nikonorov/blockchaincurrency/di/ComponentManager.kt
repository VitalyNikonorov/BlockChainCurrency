package net.nikonorov.blockchaincurrency.di

import android.content.Context
import net.nikonorov.blockchaincurrency.di.main.MainScreenComponent
import net.nikonorov.blockchaincurrency.di.main.MainScreenModule

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

object ComponentManager {

    private lateinit var appComponent: AppComponent
    val mainScreenComponent: MainScreenComponent by lazy { appComponent.plus(MainScreenModule()) }

    fun initAppComponent(context: Context) {
        appComponent = buildComponent(context)
    }

    private fun buildComponent(context: Context): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }
}
