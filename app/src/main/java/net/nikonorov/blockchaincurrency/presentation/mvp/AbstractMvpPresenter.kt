package net.nikonorov.blockchaincurrency.presentation.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
abstract class AbstractMvpPresenter<V: MvpView>(protected val router: Router): MvpPresenter<V> {

    protected var view : V? = null
    private var compositeDisposable : CompositeDisposable? = CompositeDisposable()

    override fun attachView(view : V) {
        this.view = view
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
    }

    override fun detachView() {
        view = null
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    protected fun addDisposable(d: Disposable) {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(d)
    }

}
