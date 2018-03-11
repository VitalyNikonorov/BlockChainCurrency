package net.nikonorov.blockchaincurrency

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.Screen
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenter
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenterImpl
import net.nikonorov.blockchaincurrency.presentation.main.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.internal.verification.Times
import ru.terrakok.cicerone.Router
import java.util.*

/**
 * Created by Vitaly Nikonorov on 11.03.2018.
 * email@nikonorov.net
 */
class MainPresenterTest: AbstractRxTests() {
    private lateinit var mainPresenter: MainPresenter
    private lateinit var currencyInteractor: CurrencyInteractor
    private lateinit var router: Router
    private lateinit var mainView: MainView
    private val validData = arrayListOf("btcusd", "ethusd")

    @Before
    fun setUp() {
        currencyInteractor = mock(CurrencyInteractor::class.java)
        router = mock(Router::class.java)
        mainView = mock(MainView::class.java)
        mainPresenter = MainPresenterImpl(currencyInteractor, router)
    }

    @Test
    fun checkInitLoading() {
        doAnswer { Single.just(Collections.EMPTY_LIST) }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        Mockito.verify(currencyInteractor).getPairs()
    }

    @Test
    fun checkEmptyListResponse() {
        doAnswer { Single.just(Collections.EMPTY_LIST) }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        Mockito.verify(mainView, Times(2)).setMainListVisible(false)
        Mockito.verify(mainView, Times(2)).setErrorViewVisible(false)

        //start loading
        Mockito.verify(mainView).setEmptyListVisible(false)
        Mockito.verify(mainView).setProgressBarVisible(true)
        //show result
        Mockito.verify(mainView).setProgressBarVisible(false)
        Mockito.verify(mainView).setEmptyListVisible(true)
    }

    @Test
    fun checkErrorResponse() {
        val errorSingle: Single<List<String>> = Single.error(Throwable("Some test error"))
        doAnswer { errorSingle }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        Mockito.verify(mainView, Times(2)).setEmptyListVisible(false)
        Mockito.verify(mainView, Times(2)).setMainListVisible(false)

        //start loading
        Mockito.verify(mainView).setErrorViewVisible(false)
        Mockito.verify(mainView).setProgressBarVisible(true)
        //show result
        Mockito.verify(mainView).setProgressBarVisible(false)
        Mockito.verify(mainView).setErrorViewVisible(true)
    }

    @Test
    fun checkCorrectResponse() {
        doAnswer { Single.just(validData) }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        Mockito.verify(mainView, Times(2)).setEmptyListVisible(false)
        Mockito.verify(mainView, Times(2)).setErrorViewVisible(false)

        //start loading
        Mockito.verify(mainView).setMainListVisible(false)
        Mockito.verify(mainView).setProgressBarVisible(true)
        //show result
        Mockito.verify(mainView).setProgressBarVisible(false)
        Mockito.verify(mainView).setMainListVisible(true)
        Mockito.verify(mainView).showCurrencies(validData)
    }

    @Test
    fun checkRepeatLoading() {
        doAnswer { Single.just(Collections.EMPTY_LIST) }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        mainPresenter.onRepeatLoadingClick()
        Mockito.verify(currencyInteractor, Times(2)).getPairs()
    }

    @Test
    fun checkItemSelection() {
        doAnswer { Single.just(validData) }
                .`when`(currencyInteractor)
                .getPairs()
        mainPresenter.attachView(mainView)
        mainPresenter.onItemClick(1)
        Mockito.verify(router).navigateTo(Screen.PAIR_DETAILS_SCREEN, "ethusd")
    }
}
