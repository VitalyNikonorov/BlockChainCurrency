package net.nikonorov.blockchaincurrency.presentation.main.view

import net.nikonorov.blockchaincurrency.presentation.mvp.MvpView

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
interface MainView: MvpView {
    fun setProgressBarVisible(visible: Boolean)

    fun setEmptyListVisible(visible: Boolean)

    fun setMainListVisible(visible: Boolean)

    fun showError(message: String)

    fun showCurrencies(list: List<String>)
}