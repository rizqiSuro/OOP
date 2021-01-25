package com.suro.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suro.oop2.model.Tugas

class TugasAdapter(
    private val listItems: ArrayList<Tugas>,
    private val listener: TugasListener
) : RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_tugas, parent, false)
        return TugasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }

    interface TugasListener{
        fun OnItemClicked(Tugas: Tugas)
    }
}