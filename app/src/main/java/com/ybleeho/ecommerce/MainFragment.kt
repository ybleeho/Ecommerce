package com.ybleeho.ecommerce

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ybleeho.ecommerce.model.Product
import com.ybleeho.ecommerce.repos.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jetbrains.anko.sdk27.coroutines.textChangedListener

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var searchTerm: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        progressBar = root.findViewById(R.id.progressBar)
        categoryRecyclerView = root.findViewById(R.id.categoriesRecyclerView)

        val categories = listOf<String>(
            "Jeans",
            "Socks",
            "Suits",
            "Skirts",
            "Dresses",
            "Daniel",
            "Jeans",
            "T-Shirts"
        )

        categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchTerm = view.findViewById(R.id.searchTerm)
        searchButton = view.findViewById(R.id.searchButton)

        val viewModel: MainFragmentViewModel by viewModels()
        viewModel.products.observe(requireActivity(), Observer {
            loadRecycleView(it)
        })
        viewModel.setup()

        searchButton.setOnClickListener {
            viewModel.search(searchTerm.text.toString())
        }

        searchTerm.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.search(searchTerm.text.toString())
            }
        })
    }

    private fun loadRecycleView(products: List<Product>) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(products) { title, photoUrl, price, isOnSale, photoView ->
                var intent = Intent(activity, ProductDetails::class.java)
                intent.putExtra("product", Product(title, photoUrl, price, isOnSale))
                var options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity as AppCompatActivity,
                    photoView,
                    "photoToAnimate"
                )
                startActivity(intent, options.toBundle())
            }
        }
        progressBar.visibility = View.GONE
    }

}
