package com.example.rssreader

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class RssItemAdapter(private val rssItems : List<RssItem>, private val myListFragment : MyListFragment) : RecyclerView.Adapter<RssItemAdapter.ViewHolder>(){

    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemAdapter.ViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false) as ViewHolder

    override fun onBindViewHolder(holder: RssItemAdapter.ViewHolder, position: Int) {
        val rssItem: RssItem = rssItems[position]
        holder.itemView.rsstitle.text = rssItem.title
        holder.itemView.rssurl.text = rssItem.link
        holder.itemView.rssurl.setOnClickListener(OnClickListener { myListFragment.updateDetail(rssItem.link) })
        holder.itemView.icon.setOnLongClickListener(OnLongClickListener {
            myListFragment.goToActionMode(rssItem)
            true
        })
    }

    override fun getItemCount() : Int = rssItems.size
}