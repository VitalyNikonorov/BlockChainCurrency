package net.nikonorov.blockchaincurrency.presentation.info.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoView
import net.nikonorov.blockchaincurrency.presentation.mvp.AbstractMvpPresenter

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
class PairInfoPresenterImpl(private val currencyInteractor: CurrencyInteractor) : AbstractMvpPresenter<PairInfoView>(), PairInfoPresenter {

    private val pair = "btcusd"

    override fun attachView(view: PairInfoView) {
        super.attachView(view)
        currencyInteractor.getPairInfo(pair)
                .doOnSubscribe(this::addDisposable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Received data: ", it.toString())
                }, {
                    it.printStackTrace()
                })
    }

}