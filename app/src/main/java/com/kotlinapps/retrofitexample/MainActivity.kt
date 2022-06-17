package com.kotlinapps.retrofitexample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryRecyclerAdapter: Adapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        retrofit()
    }

    private fun retrofit() {
        val apiServices = ApiServices.create().getMovie()

        apiServices.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.body() != null) {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    categoryRecyclerAdapter.setMovieListItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                onSNACK(recyclerView)
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun setupView() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        categoryRecyclerAdapter = Adapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = categoryRecyclerAdapter
    }

    fun onSNACK(view: View) {
        val snackbar = Snackbar.make(view, "No Internet Connection", 200000)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.BLACK)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 18F
        snackbar.show()
    }
}