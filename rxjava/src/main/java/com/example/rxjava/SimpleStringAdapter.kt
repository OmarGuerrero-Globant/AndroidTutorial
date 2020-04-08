package com.example.rxjava

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class SimpleStringAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SimpleStringAdapter.ViewHolder>() {
    private val mStrings: MutableList<String> = ArrayList()
    fun setStrings(newStrings: List<String>) {
        mStrings.clear()
        mStrings.addAll(newStrings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.string_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.colorTextView.text = mStrings[position]
        holder.itemView.setOnClickListener { Toast.makeText(mContext, mStrings[position], Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int = mStrings.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorTextView: TextView = view.findViewById(R.id.color_display)
    }

}