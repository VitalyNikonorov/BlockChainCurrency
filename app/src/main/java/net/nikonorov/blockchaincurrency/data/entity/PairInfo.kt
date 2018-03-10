package net.nikonorov.blockchaincurrency.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 *
 * Price has String type because Float has rounding error
 * more info 'https://docs.bitfinex.com/reference#rest-public-ticker'
 */
data class PairInfo(
    @SerializedName("mid") val midPrice: String,
    @SerializedName("bid") val bidPrice: String,
    @SerializedName("ask") val askPrice: String,
    @SerializedName("last_price") val lastPrice: String,
    @SerializedName("low") val lowestPrice: String,
    @SerializedName("high") val highestPrice: String,
    @SerializedName("volume") val tradeVolume: String,
    @SerializedName("timestamp") val timeStamp: String
)
