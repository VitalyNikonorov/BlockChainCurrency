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

    private val currencies: MutableList<String> = ArrayList()

    override fun attachView(view: MainView) {
        super.attachView(view)
        currencyInteractor.getCurrencies()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::addDisposable)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    this.view?.setEmptyListVisible(false)
                    this.view?.setProgressBarVisible(true)
                    this.view?.setMainListVisible(false)
                })
                .subscribe({
                    currencies.clear()
                    currencies.addAll(it)
                    Log.d("Data received: ", it.toString())
                    this.view?.setProgressBarVisible(false)
                    this.view?.setEmptyListVisible(currencies.isEmpty())
                    this.view?.setMainListVisible(!currencies.isEmpty())
                    this.view?.showCurrencies(currencies)
                }, { it.printStackTrace() })
    }

}
