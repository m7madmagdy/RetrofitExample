package com.kotlinapps.retrofitexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryRecyclerView(
    val context: Context,
) : RecyclerView.Adapter<CategoryRecyclerView.MyViewHolder>() {

    private var categoryList: List<Category> = ArrayList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)

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
        private val imgUrl: ImageView = itemView.findViewById(R.id.img)
        private val idTxt: TextView = itemView.findViewById(R.id.Id)
        private val itemList: LinearLayout = itemView.findViewById(R.id.linearItem)

        fun bind(category: Category) {
            idTxt.text = category.id.toString()
            Picasso.get().load(category.url).into(imgUrl)


            itemList.setOnClickListener {
                Toast.makeText(itemView.context, "${category.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}