package net.nikonorov.blockchaincurrency.presentation.main.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.nikonorov.blockchaincurrency.R

/**
 * Created by Vitaly Nikonorov on 09.03.2018.
 * email@nikonorov.net
 */
class CurrencyAdapter: RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private val items: MutableList<String> = ArrayList()

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.caption.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currencies_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CurrencyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var caption: TextView = itemView.findViewById(R.id.item_caption)
    }

    fun setData(data: List<String>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}
