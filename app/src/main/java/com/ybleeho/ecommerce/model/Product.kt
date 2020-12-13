package com.ybleeho.ecommerce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("name")
    val title: String,
    @SerializedName("photo_url")
    val photoUrl: String,
    val price: Double,
    val isOnSale: Boolean
) : Serializable