package com.ybleeho.ecommerce

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.d
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.ybleeho.ecommerce.model.Product
import com.ybleeho.ecommerce.productdetails.ProductDetailsViewModel

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        val product = intent.getSerializableExtra("product") as Product

        val viewModel: ProductDetailsViewModel by viewModels()
        viewModel.fetchProductDetails(product.title)
        viewModel.productDetails.observe(this, Observer<Product> { product ->
            d("ViewModel", "observe something $product")
        })

        findViewById<TextView>(R.id.product_name).text = product.title
        findViewById<TextView>(R.id.product_price).text = product.price.toString()

        val detailPhoto: ImageView = findViewById<ImageView>(R.id.detail_photo)
        Picasso.get().load(product.photoUrl).into(detailPhoto)

        findViewById<Button>(R.id.availability).setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title in stock!")
                .setPositiveButton("Ok") { p0, p1 ->

                }
                .create()
                .show()
        }

    }
}