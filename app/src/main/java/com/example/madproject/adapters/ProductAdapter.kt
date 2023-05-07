package com.example.madproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.R
import com.example.madproject.models.ProductModel

class ProductAdapter (private val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(){


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClickListener(position: Int)
    }

    fun setonItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_list,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
       val currentProduct = productList[position]
        holder.tvProductName.text = currentProduct.PersonName
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvProductName : TextView = itemView.findViewById(R.id.tvProductName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClickListener(adapterPosition)
            }
        }

    }


}