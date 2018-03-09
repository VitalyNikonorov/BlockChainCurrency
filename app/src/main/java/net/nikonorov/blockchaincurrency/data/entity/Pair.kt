package net.nikonorov.blockchaincurrency.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
data class Pair(
        @SerializedName("pair") val name: String,
        @SerializedName("price_precision") val precision: Int,
        @SerializedName("initial_margin") val initialMargin: Float,
        @SerializedName("minimum_margin") val minimumMargin: Float,
        @SerializedName("maximum_order_size") val maxOrderSize: Float,
        @SerializedName("minimum_order_size") val minOrderSize: Float,
        @SerializedName("expiration") val expiration: String,
        @SerializedName("margin") val margin: Boolean
)
