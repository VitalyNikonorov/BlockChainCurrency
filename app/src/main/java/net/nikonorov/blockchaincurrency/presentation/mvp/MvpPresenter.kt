package net.nikonorov.blockchaincurrency.presentation.mvp

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
interface MvpPresenter<in V: MvpView> {

    fun attachView(view : V)

    fun detachView()

}
