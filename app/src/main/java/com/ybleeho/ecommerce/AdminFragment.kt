package com.ybleeho.ecommerce

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.ybleeho.ecommerce.database.AppDatabase
import com.ybleeho.ecommerce.database.ProductFromDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread

class AdminFragment : Fragment() {

    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton = view.findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            var title = view.findViewById<EditText>(R.id.productTitle).text
            doAsync {
                val db = Room.databaseBuilder(
                    activity!!,
                    AppDatabase::class.java, "database-name"
                ).build()
                db.productDao().insertAll(ProductFromDatabase(null, title.toString(), 1.99))
            }
            toast("Added product success!")
        }

    }
}