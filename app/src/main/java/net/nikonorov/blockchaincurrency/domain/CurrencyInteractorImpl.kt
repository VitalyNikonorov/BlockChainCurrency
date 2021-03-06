package net.nikonorov.blockchaincurrency.domain

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.NetworkClient
import net.nikonorov.blockchaincurrency.data.entity.PairInfo

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
class CurrencyInteractorImpl(private val networkClient: NetworkClient): CurrencyInteractor {

    override fun getPairInfo(pair: String): Single<PairInfo> {
        return networkClient.getPairInfo(pair)
    }

    override fun getPairs(): Single<List<String>> {
        return networkClient.getPairs()
    }


}
