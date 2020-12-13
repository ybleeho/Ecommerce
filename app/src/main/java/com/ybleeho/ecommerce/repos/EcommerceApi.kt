package com.ybleeho.ecommerce.repos

import com.ybleeho.ecommerce.model.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("data/products.json")
    suspend fun fetchAllProducts() : List<Product>
}