package net.nikonorov.blockchaincurrency

import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenter
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenterImpl
import net.nikonorov.blockchaincurrency.presentation.main.view.MainView
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import ru.terrakok.cicerone.Router
import java.util.*

/**
 * Created by Vitaly Nikonorov on 11.03.2018.
 * email@nikonorov.net
 */
class MainPresenterTest {
    private lateinit var mainPresenter: MainPresenter
    private lateinit var currencyInteractor: CurrencyInteractor
    private lateinit var router: Router
    private lateinit var mainView: MainView

    companion object {
        @BeforeClass @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }
    }

    @Before
    fun setUp() {
        currencyInteractor = mock(CurrencyInteractor::class.java)
        router = mock(Router::class.java)
        mainView = mock(MainView::class.java)
        mainPresenter = MainPresenterImpl(currencyInteractor, router)
        doAnswer { Single.just(Collections.EMPTY_LIST) }
                .`when`(currencyInteractor)
                .getPairs()
    }

    @Test
    fun checkInitLoading() {
        mainPresenter.attachView(mainView)
        Mockito.verify(currencyInteractor).getPairs()
    }
}
