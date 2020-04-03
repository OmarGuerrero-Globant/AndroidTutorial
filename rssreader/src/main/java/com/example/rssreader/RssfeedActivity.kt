package com.example.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class RssfeedActivity : AppCompatActivity(),
    MyListFragment.OnItemSelectedListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed)
        if(resources.getBoolean(R.bool.twoPaneMode)){return}
        if(savedInstanceState != null){
            fm.executePendingTransactions()
            val fragmentById: Fragment? = fm.findFragmentById(R.id.fragment_container)
            if (fragmentById !=null){
                fm.beginTransaction()
                    .remove(fragmentById).hashCode()
            }
        }
        val listFragment: MyListFragment = MyListFragment()
        fm.beginTransaction().replace(R.id.fragment_container, listFragment).commit()
    }

    override fun onRssItemSelected(text: String?) {
        if (resources.getBoolean(R.bool.twoPaneMode)){
            val fragment : DetailFragment = fm.findFragmentById(R.id.detailFragment) as DetailFragment
            fragment.setText(text)
        }else{
            val transaction: FragmentTransaction = fm.beginTransaction()
            transaction.replace(R.id.fragment_container, newInstance(text))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
