package com.suro.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suro.oop2.model.Mk

class MkAdapter(
    private val listItems: ArrayList<Mk>,
    private val listener: MkListener
) : RecyclerView.Adapter<MkAdapter.MkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_mk, parent, false)
        return MkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MkViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class MkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }

    interface MkListener{
        fun OnItemClicked(Mk: Mk)
    }
}