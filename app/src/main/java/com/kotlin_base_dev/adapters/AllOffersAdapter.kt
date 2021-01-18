package com.kotlin_base_dev.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin_base_dev.R
import com.kotlin_base_dev.network.models.getmodels.Listoffers

class AllOffersAdapter(private val data: List<Listoffers>) :
    RecyclerView.Adapter<AllOffersAdapter.MyViewHolder>() {


    //declaring main-attributes fields
    lateinit private var campaing:String
    lateinit private var campaign_id:String
    lateinit private var creative_id:String
    lateinit private var creative:String
    lateinit private var adgroup:String
    lateinit private var adgroup_id:String
    lateinit private var string:String



    override fun getItemCount() = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.default_container, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView?.text = data[position].description
        holder.smallTextView?.text = "кот"
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null

        init {
         //   largeTextView = itemView.findViewById(R.id.textViewLarge)
          //  smallTextView = itemView.findViewById(R.id.textViewSmall)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}