package net.nikonorov.blockchaincurrency.di.main

import dagger.Module
import dagger.Provides
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenter
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenterImpl

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
@Module
class MainScreenModule {

    @Provides
    @MainScreenSope
    fun provideMainPresenter() : MainPresenter = MainPresenterImpl()

}