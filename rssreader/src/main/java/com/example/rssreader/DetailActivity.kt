package com.example.rssreader

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }
        setContentView(R.layout.activity_detail)
        val extras = intent.extras
        if (extras != null) {
            val url = extras.getString(EXTRA_URL)
            val detailFragment = supportFragmentManager
                .findFragmentById(R.id.detailFragment) as DetailFragment
            detailFragment.setText(url)
        }
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}