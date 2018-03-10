package net.nikonorov.blockchaincurrency.presentation.info.presenter

import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoView
import net.nikonorov.blockchaincurrency.presentation.mvp.MvpPresenter

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
interface PairInfoPresenter: MvpPresenter<PairInfoView> {

    fun initPair(pair: String)

}