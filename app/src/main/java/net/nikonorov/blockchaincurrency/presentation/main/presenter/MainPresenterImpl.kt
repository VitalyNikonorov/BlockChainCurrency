package net.nikonorov.blockchaincurrency.presentation.main.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.main.view.MainView
import net.nikonorov.blockchaincurrency.presentation.mvp.AbstractMvpPresenter

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class MainPresenterImpl(private val currencyInteractor: CurrencyInteractor) : AbstractMvpPresenter<MainView>(), MainPresenter {

    override fun attachView(view: MainView) {
        super.attachView(view)
        currencyInteractor.getCurrencies()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::addDisposable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Data received: ", it.toString())
                }, { it.printStackTrace() })
    }

}
