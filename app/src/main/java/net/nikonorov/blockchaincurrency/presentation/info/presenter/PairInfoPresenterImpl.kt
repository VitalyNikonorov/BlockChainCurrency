package net.nikonorov.blockchaincurrency.presentation.info.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoView
import net.nikonorov.blockchaincurrency.presentation.mvp.AbstractMvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 *
 */
class PairInfoPresenterImpl(private val currencyInteractor: CurrencyInteractor, router: Router) : AbstractMvpPresenter<PairInfoView>(router), PairInfoPresenter {
    private lateinit var pair: String

    /**
     * Here we suppose than data is rapidly changing and we do not cache it
     * If you wanna reduce server calls, you can add caching data here or in repository
     * and add some expiration period, after which data will be not valid
     */
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

    override fun initPair(pair: String) {
        this.pair = pair
    }

}
