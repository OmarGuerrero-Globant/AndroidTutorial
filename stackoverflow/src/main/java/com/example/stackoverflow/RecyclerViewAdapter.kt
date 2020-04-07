package com.example.stackoverflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_spinner_dropdown_item.view.text1


class RecyclerViewAdapter(private val data: List<Answer>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_selectable_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val answer : Answer = data[position]
        holder.itemView.tag = answer.answerId
        holder.itemView.text1.text = "$answer"
    }

    override fun getItemCount(): Int = data.size

}