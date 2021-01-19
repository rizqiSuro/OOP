package com.satria.OOP2_CRUD

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satria.OOP2_CRUD.Database.hp
import kotlinx.android.synthetic.main.adapter_hp.view.*

class HpAdapter (private val AllHp: ArrayList<hp>, private val listener: OnAdapterListener) : RecyclerView.Adapter<HpAdapter.HpViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HpViewHolder {
        return HpViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_hp, parent, false)
        )
    }

    override fun getItemCount() = AllHp.size

    override fun onBindViewHolder(holder: HpViewHolder, position: Int) {
        val hp = AllHp[position]
        holder.view.text_merk.text = hp.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(hp)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(hp)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(hp)
        }

    }

    class HpViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<hp>) {
        AllHp.clear()
        AllHp.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(hp: hp)
        fun onDelete(hp: hp)
        fun onUpdate(hp: hp)
    }
}