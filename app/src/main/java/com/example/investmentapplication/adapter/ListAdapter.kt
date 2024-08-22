package com.example.investmentapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investmentapplication.R
import com.example.investmentapplication.models.InvestmentItem
import com.example.investmentapplication.utils.Constants.DOLLAR

class ListAdapter(
    private val items: List<InvestmentItem>, private val onClick: (InvestmentItem) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.item_name)
        private val value: TextView = itemView.findViewById(R.id.item_value)
        private val principal: TextView = itemView.findViewById(R.id.item_principal)
        fun bind(item: InvestmentItem) {
            name.text = item.name
            value.text = DOLLAR + item.value.toString()
            principal.text = DOLLAR + item.principal.toString()
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }
}
