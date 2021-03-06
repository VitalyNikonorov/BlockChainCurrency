package net.nikonorov.blockchaincurrency.di

import android.content.Context
import net.nikonorov.blockchaincurrency.di.main.MainScreenComponent
import net.nikonorov.blockchaincurrency.di.main.MainScreenModule
import net.nikonorov.blockchaincurrency.di.pairinfo.PairInfoModule
import net.nikonorov.blockchaincurrency.di.pairinfo.PairInfoScreenComponent

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

object ComponentManager {

    lateinit var appComponent: AppComponent
        private set
    private var mainScreenComponent: MainScreenComponent? = null
    private var pairInfoScreenComponent: PairInfoScreenComponent? = null

    fun initAppComponent(context: Context) {
        appComponent = buildComponent(context)
    }

    private fun buildComponent(context: Context): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }

    @Synchronized fun getMainScreenComponent(): MainScreenComponent {
        if (mainScreenComponent == null) {
            mainScreenComponent = appComponent.plus(MainScreenModule())
        }
        return mainScreenComponent as MainScreenComponent
    }

    @Synchronized fun getPairInfoScreenComponent(): PairInfoScreenComponent {
        if (pairInfoScreenComponent == null) {
            pairInfoScreenComponent = appComponent.plus(PairInfoModule())
        }
        return pairInfoScreenComponent as PairInfoScreenComponent
    }

    @Synchronized fun removeMainScreenComponent() {
        mainScreenComponent = null
    }

    @Synchronized fun removePairInfoScreenComponent() {
        pairInfoScreenComponent = null
    }
}
