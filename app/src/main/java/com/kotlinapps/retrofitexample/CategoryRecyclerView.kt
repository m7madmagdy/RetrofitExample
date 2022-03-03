package com.kotlinapps.retrofitexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryRecyclerView(val context: Context) :
    RecyclerView.Adapter<CategoryRecyclerView.MyViewHolder>() {

    private var categoryList: List<Category> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieItem = categoryList[position]
        holder.titleTxt.text = movieItem.title
        Picasso.get().load(movieItem.url).into(holder.imgUrl)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setMovieList1")
    fun setMovieListItems(categoryList: List<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    //ViewHolder
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgUrl: ImageView = itemView.findViewById(R.id.img)
        val titleTxt: TextView = itemView.findViewById(R.id.title)
    }
}