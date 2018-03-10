package net.nikonorov.blockchaincurrency.data

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.entity.PairInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
interface Api {

    @GET("symbols/")
    fun getPairs(): Single<List<String>>

    @GET("pubticker/{pair}")
    fun getPairInfo(@Path("pair") pair: String): Single<PairInfo>

}
