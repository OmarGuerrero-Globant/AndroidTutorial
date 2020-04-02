package com.example.rssreader

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_rsslist_overview.view.*


class MyListFragment : Fragment() {
    private lateinit var listener: OnItemSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is OnItemSelectedListener) {
            context
        } else {
            throw ClassCastException(
                "$context must implement MyListFragment.OnItemSelectedListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(
            R.layout.fragment_rsslist_overview,
            container, false
        )
        view.updateButton.setOnClickListener { updateDetail("testing") }
        return view
    }

    private fun updateDetail(uri: String) {
        val newTime =
            System.currentTimeMillis().toString()
        listener.onRssItemSelected(newTime)
    }

    interface OnItemSelectedListener {
        fun onRssItemSelected(text: String?)
    }
}