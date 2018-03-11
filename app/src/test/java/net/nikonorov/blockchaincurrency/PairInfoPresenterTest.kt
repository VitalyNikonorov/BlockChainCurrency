package net.nikonorov.blockchaincurrency

import com.nhaarman.mockito_kotlin.never
import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.entity.PairInfo
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.info.presenter.PairInfoPresenter
import net.nikonorov.blockchaincurrency.presentation.info.presenter.PairInfoPresenterImpl
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.internal.verification.Times
import ru.terrakok.cicerone.Router

/**
 * Created by Vitaly Nikonorov on 11.03.2018.
 * email@nikonorov.net
 */
class PairInfoPresenterTest: AbstractRxTests() {
    private lateinit var presenter: PairInfoPresenter
    private lateinit var currencyInteractor: CurrencyInteractor
    private lateinit var view: PairInfoView
    private lateinit var router: Router
    private val testPair = "btcusd"
    private val validTestPairInfo = PairInfo("9212.05", "9212.0","9212.1",
            "9220.1","8428.0","9500.0","58983.55216079","1520773015.41036")

    @Before
    fun setUp() {
        currencyInteractor = mock(CurrencyInteractor::class.java)
        view = mock(PairInfoView::class.java)
        router = mock(Router::class.java)
        presenter = PairInfoPresenterImpl(currencyInteractor, router)
    }

    @Test
    fun checkInitLoading() {
        doAnswer { Single.just(validTestPairInfo) }
                .`when`(currencyInteractor)
                .getPairInfo(testPair)
        presenter.initPair(testPair)
        presenter.attachView(view)
        Mockito.verify(currencyInteractor).getPairInfo(testPair)

        Mockito.verify(view, Times(2)).setErrorViewVisible(false)

        //start loading
        Mockito.verify(view).setProgressBarVisible(true)
        Mockito.verify(view).setMainViewVisible(false)

        //show result
        Mockito.verify(view).setProgressBarVisible(false)
        Mockito.verify(view).setMainViewVisible(true)
        Mockito.verify(view).showInfo(validTestPairInfo)
    }

    @Test
    fun checkErrorResponse() {
        val errorSingle: Single<PairInfo> = Single.error(Throwable("Some test error"))
        doAnswer { errorSingle }
                .`when`(currencyInteractor)
                .getPairInfo(testPair)

        presenter.initPair(testPair)
        presenter.attachView(view)

        Mockito.verify(view, Times(2)).setMainViewVisible(false)

        //start loading
        Mockito.verify(view).setProgressBarVisible(true)
        Mockito.verify(view).setErrorViewVisible(false)

        //show result
        Mockito.verify(view).setProgressBarVisible(false)
        Mockito.verify(view).setErrorViewVisible(true)
        Mockito.verify(view, never()).showInfo(validTestPairInfo)
    }

    @Test
    fun checkRetryLoading() {
        doAnswer { Single.just(validTestPairInfo) }
                .`when`(currencyInteractor)
                .getPairInfo(testPair)
        presenter.initPair(testPair)
        presenter.attachView(view)
        presenter.onRetryLoadingClick()

        Mockito.verify(currencyInteractor, Times(2)).getPairInfo(testPair)
    }
}