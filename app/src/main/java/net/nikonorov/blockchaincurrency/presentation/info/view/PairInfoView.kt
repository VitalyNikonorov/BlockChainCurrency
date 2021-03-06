package net.nikonorov.blockchaincurrency.presentation.info.view

import net.nikonorov.blockchaincurrency.data.entity.PairInfo
import net.nikonorov.blockchaincurrency.presentation.mvp.MvpView

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
interface PairInfoView: MvpView {

    fun setProgressBarVisible(visible: Boolean)

    fun setMainViewVisible(visible: Boolean)

    fun showInfo(pairInfo: PairInfo)

    fun setErrorViewVisible(visible: Boolean)
}
