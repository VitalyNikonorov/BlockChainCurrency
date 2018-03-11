package net.nikonorov.blockchaincurrency.presentation.info.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoView
import net.nikonorov.blockchaincurrency.presentation.mvp.AbstractMvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 *
 */
class PairInfoPresenterImpl(private val currencyInteractor: CurrencyInteractor, router: Router) : AbstractMvpPresenter<PairInfoView>(router), PairInfoPresenter {
    private lateinit var pair: String
    private var pairInfoDisposable: Disposable? = null

    /**
     * Here we suppose than data is rapidly changing and we do not cache it
     * If you wanna reduce server calls, you can add caching data here or in repository
     * and add some expiration period, after which data will be not valid
     */
    override fun attachView(view: PairInfoView) {
        super.attachView(view)
        loadPairInfo()
    }

    private fun loadPairInfo() {
        pairInfoDisposable?.dispose()
        pairInfoDisposable = currencyInteractor.getPairInfo(pair)
                .doOnSubscribe(this::addDisposable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    this.view?.setProgressBarVisible(true)
                    this.view?.setMainViewVisible(false)
                    this.view?.setErrorViewVisible(false)
                })
                .subscribe({
                    Timber.d("Received data: $it")
                    this.view?.setProgressBarVisible(false)
                    this.view?.setMainViewVisible(true)
                    this.view?.setErrorViewVisible(false)
                    this.view?.showInfo(it)
                }, {
                    Timber.e(it)
                    this.view?.setProgressBarVisible(false)
                    this.view?.setMainViewVisible(false)
                    this.view?.setErrorViewVisible(true)
                })
    }

    override fun initPair(pair: String) {
        this.pair = pair
        Timber.d("Pair: $pair initialized")
    }

    override fun onRetryLoadingClick() {
        loadPairInfo()
    }

}
