package com.ybleeho.ecommerce.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ybleeho.ecommerce.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()

    fun fetchProductDetails(productTitle: String) {
        // coroutines
        viewModelScope.launch(Dispatchers.Default) {
            var json = URL("https://finepointmobile.com/data/products.json").readText()
            val product = Gson().fromJson(json, Array<Product>::class.java).toList().first{it.title == productTitle}
            productDetails.postValue(product)
        }
    }

}