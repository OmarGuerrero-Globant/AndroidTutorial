package com.example.binding2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


abstract class MyBaseAdapter : RecyclerView.Adapter<MyBaseAdapter.MyViewHolder>() {
    inner class MyViewHolder(
        private val binding: ViewDataBinding
    ) : ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            getLayoutIdForType(viewType),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getDataAtPosition(position))
    }

    abstract fun getDataAtPosition(position: Int): Any
    abstract fun getLayoutIdForType(viewType: Int): Int
}