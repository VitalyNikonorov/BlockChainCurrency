package net.nikonorov.blockchaincurrency.domain

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.NetworkClient

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
class CurrencyInteractorImpl(private val networkClient: NetworkClient): CurrencyInteractor {

    override fun getCurrencies(): Single<List<String>> {
        return networkClient.getCurrencies()
    }

}