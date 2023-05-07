package com.example.finalproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.models.CustomerModel

class CusAdapter(private val cusList:ArrayList<CustomerModel>) :
    RecyclerView.Adapter<CusAdapter.ViewHolder>(){

   private lateinit var mListener: onItemClickListener


   interface onItemClickListener{
      fun onItemClick(position: Int)
   }

   fun setOnItemClickListener(clickListener:onItemClickListener) {
       mListener=clickListener
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_show, parent,false )
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentCus = cusList[position]
        holder.tvCusName.text = currentCus.cusName
    }

    override fun getItemCount(): Int {
        return cusList.size//return customer list size
    }

    class ViewHolder (itemView: View,  clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvCusName: TextView = itemView.findViewById(R.id.tvCusName)
        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}

