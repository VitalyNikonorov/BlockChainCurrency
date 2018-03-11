package net.nikonorov.blockchaincurrency.data

import io.reactivex.Single
import net.nikonorov.blockchaincurrency.data.entity.PairInfo
import retrofit2.Retrofit

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
class NetworkClient(builder: Retrofit.Builder) {
    companion object {
        const val BASE_API_URL = "https://api.bitfinex.com/v1/"
    }
    private val api: Api

    init {
        api = builder.baseUrl(BASE_API_URL).build().create(Api::class.java)
    }

    fun getPairs(): Single<List<String>> {
        return api.getPairs()
    }

    fun getPairInfo(pair: String): Single<PairInfo> {
        return api.getPairInfo(pair)
    }
}
