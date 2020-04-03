package com.example.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


class RssfeedActivity : AppCompatActivity(),
    MyListFragment.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed)
    }

    override fun onRssItemSelected(text: String?) {

        val fm: FragmentManager = supportFragmentManager
        (fm
            .findFragmentById(R.id.detailFragment) as DetailFragment).apply {
            this.setText(text)
        }
    }
}
