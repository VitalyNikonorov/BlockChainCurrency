package net.nikonorov.blockchaincurrency.domain

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.entity.PairInfo

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
interface CurrencyInteractor {

    fun getPairs(): Single<List<String>>

    fun getPairInfo(pair: String): Single<PairInfo>
}