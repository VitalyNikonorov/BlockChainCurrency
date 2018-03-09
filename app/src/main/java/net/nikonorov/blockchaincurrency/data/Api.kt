package net.nikonorov.blockchaincurrency.data

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
interface Api {

    @GET("symbols/")
    fun getPairs(): Single<List<String>>

}
