package com.example.rssreader

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity


class RssfeedActivity : AppCompatActivity(),
    MyListFragment.OnItemSelectedListener,
    ActionMode.Callback {

    private var stateFragment: SelectionStateFragment? = null
    companion object{
        @JvmOverloads
        @JvmStatic
        fun newInstance(text: String? = null):DetailFragment{
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(DetailFragment.EXTRA_TEXT, text)
                }
            }
        }
    }

    private val fm: FragmentManager = supportFragmentManager
    private var selectedRssItem : RssItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed)
        val tb: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setActionBar(tb)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val tb: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        tb.inflateMenu(R.menu.mainmenu)
        tb.setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                val fragment = (supportFragmentManager
                    .findFragmentById(R.id.listFragment) as MyListFragment).apply {
                    updateListContent()
                }
            }
            R.id.action_settings -> {
                val intent = Intent(this, MyPreferences::class.java).apply {
                    startActivity(this)
                }
                Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return true
    }

    override fun onRssItemSelected(link: String?) {
        if (resources.getBoolean(R.bool.twoPaneMode)) {
            val fragment = (supportFragmentManager
                .findFragmentById(R.id.detailFragment) as DetailFragment).apply {
                setText(link)
            }
        } else {
            val intent = Intent(
                applicationContext,
                DetailActivity::class.java
            ).apply {
                putExtra(DetailActivity.EXTRA_URL, link)
                startActivity(this)
            }
        }
    }

    override fun goToActionMode(item: RssItem) {
        selectedRssItem = item
        startActionMode(this)
    }

    override fun onCreateActionMode(
        mode: ActionMode,
        menu: Menu
    ): Boolean {
        val inflater: MenuInflater? = mode.menuInflater
        inflater?.inflate(R.menu.actionmode, menu)
        return true
    }

    override fun onPrepareActionMode(
        mode: ActionMode,
        menu: Menu
    ): Boolean = false

    override fun onActionItemClicked(
        mode: ActionMode,
        item: MenuItem
    ): Boolean {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(
                Intent.EXTRA_TEXT, "I found this interesting link" +
                        selectedRssItem?.link
            )
            type = "text/plain"
            startActivity(this)
        }
        mode.finish()
        selectedRssItem = null
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode) {}
}
