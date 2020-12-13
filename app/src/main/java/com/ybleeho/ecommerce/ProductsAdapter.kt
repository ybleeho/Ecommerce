package com.ybleeho.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ybleeho.ecommerce.model.Product

class ProductsAdapter(
    private val products: List<Product>,
    private val onClickProduct: (title: String, photoUrl: String, price: Double, isOnSale: Boolean, photoView: ImageView) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.photo)
        val price: TextView = itemView.findViewById(R.id.price)
        val isOnSale: ImageView = itemView.findViewById(R.id.saleImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()
        if (product.isOnSale) {
            holder.isOnSale.visibility = View.VISIBLE
        } else {
            holder.isOnSale.visibility = View.GONE
        }

        holder.image.setOnClickListener {
            onClickProduct.invoke(product.title, product.photoUrl, product.price, product.isOnSale, holder.image)
        }

    }

    override fun getItemCount() = products.size


}