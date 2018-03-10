package net.nikonorov.blockchaincurrency.di.pairinfo

import dagger.Module
import dagger.Provides
import net.nikonorov.blockchaincurrency.domain.CurrencyInteractor
import net.nikonorov.blockchaincurrency.presentation.info.presenter.PairInfoPresenter
import net.nikonorov.blockchaincurrency.presentation.info.presenter.PairInfoPresenterImpl

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
@Module
class PairInfoModule {
    @Provides
    @PairInfoScreenScope
    fun provideMainPresenter(currencyInteractor: CurrencyInteractor) : PairInfoPresenter = PairInfoPresenterImpl(currencyInteractor)
}
