package net.nikonorov.blockchaincurrency.presentation.main.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.Screen
import net.nikonorov.blockchaincurrency.presentation.main.view.MainView
import net.nikonorov.blockchaincurrency.presentation.mvp.AbstractMvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class MainPresenterImpl(private val currencyInteractor: CurrencyInteractor, router: Router) : AbstractMvpPresenter<MainView>(router), MainPresenter {
    private val pairs: MutableList<String> = ArrayList()
    private var loadingPairsDisposable: Disposable? = null

    override fun attachView(view: MainView) {
        super.attachView(view)
        loadPairs()
    }

    private fun loadPairs() {
        loadingPairsDisposable?.dispose()
        loadingPairsDisposable = currencyInteractor.getPairs()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::addDisposable)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    this.view?.setEmptyListVisible(false)
                    this.view?.setProgressBarVisible(true)
                    this.view?.setMainListVisible(false)
                    this.view?.setErrorViewVisible(false)
                })
                .subscribe({
                    pairs.clear()
                    pairs.addAll(it)
                    Timber.d(it.toString())
                    this.view?.setProgressBarVisible(false)
                    this.view?.setEmptyListVisible(pairs.isEmpty())
                    this.view?.setMainListVisible(!pairs.isEmpty())
                    this.view?.showCurrencies(pairs)
                    this.view?.setErrorViewVisible(false)
                }, {
                    it.printStackTrace()
                    this.view?.setEmptyListVisible(false)
                    this.view?.setProgressBarVisible(false)
                    this.view?.setMainListVisible(false)
                    this.view?.setErrorViewVisible(true)
                })
    }

    override fun onItemClick(position: Int) {
        router.navigateTo(Screen.PAIR_DETAILS_SCREEN, pairs[position])
    }

    override fun onRepeatLoadingClick() {
        loadPairs()
    }

}
