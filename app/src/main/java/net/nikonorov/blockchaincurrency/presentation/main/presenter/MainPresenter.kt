package net.nikonorov.blockchaincurrency.presentation.main.presenter

import net.nikonorov.blockchaincurrency.presentation.main.view.MainView
import net.nikonorov.blockchaincurrency.presentation.main.view.PairAdapter
import net.nikonorov.blockchaincurrency.presentation.mvp.MvpPresenter

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
interface MainPresenter: MvpPresenter<MainView>, PairAdapter.OnPairItemClickListener {

    fun onRepeatLoadingClick()

}
