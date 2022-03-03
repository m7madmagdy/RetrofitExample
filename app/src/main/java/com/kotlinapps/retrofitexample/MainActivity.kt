package com.kotlinapps.retrofitexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var categoryRecyclerView: CategoryRecyclerView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        categoryRecyclerView = CategoryRecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = categoryRecyclerView
        progressBar = findViewById(R.id.progressBar)

        val apiServices = ApiServices.create().getMovie()
        apiServices.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {

                if (response.body() != null) {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    categoryRecyclerView.setMovieListItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                onSNACK(recyclerView)
                progressBar.visibility = View.GONE
            }
        })
    }

    fun onSNACK(view: View) {
        val snackbar = Snackbar.make(
            view, "Not Internet Connection",10000
        ).setAction("OK", null)
        snackbar.setActionTextColor(Color.WHITE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.BLACK)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16f
        snackbar.show()
    }
}