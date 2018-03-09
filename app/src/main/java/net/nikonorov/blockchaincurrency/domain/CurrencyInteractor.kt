package net.nikonorov.blockchaincurrency.domain

import io.reactivex.Single

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
interface CurrencyInteractor {

    fun getPairs(): Single<List<String>>

}