package com.example.rssreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_rssitem_detail.*


class DetailFragment : Fragment() {

    companion object{
        const val EXTRA_TEXT: String = "text"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.fragment_rssitem_detail,
        container, false
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle : Bundle? = arguments
        val text : String? = bundle?.getString(EXTRA_TEXT)
        setText(text)
    }

    fun setText(text: String?) {
        detailsText.text = text
    }
}