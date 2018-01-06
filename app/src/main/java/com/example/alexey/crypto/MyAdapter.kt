package com.example.alexey.crypto

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by Alexey on 05.01.2018.
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.CoinsViewHolder>() {

    private var coinsList: List<NamePrice> = emptyList()

    fun setCoinsList(list: List<NamePrice>) {
        this.coinsList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bind(coinsList[position])
    }

    override fun getItemCount(): Int = coinsList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CoinsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return CoinsViewHolder(view)
    }

    class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var name: TextView = itemView.findViewById(R.id.name_crypto)
        private var price: TextView = itemView.findViewById(R.id.price_crypto)

        fun bind(list: NamePrice) {
            name.text = list.name
            price.text = list.price.toString()
        }

    }


}
