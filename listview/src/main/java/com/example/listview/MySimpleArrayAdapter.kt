package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MySimpleArrayAdapter(
    context: Context,
    private val values: Array<String>
) :
    ArrayAdapter<String?>(context, R.layout.row_layout, values) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.row_layout, parent, false)
        val textView = rowView.findViewById<View>(R.id.label) as TextView
        val imageView: ImageView = rowView.findViewById<View>(R.id.icon) as ImageView
        textView.text = values[position]
        // Change the icon for Windows and iPhone
        val s = values[position]
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
            || s.startsWith("Solaris")
        ) {
            imageView.setImageResource(R.drawable.ic_no)
        } else {
            imageView.setImageResource(R.drawable.ic_yes)
        }
        return rowView
    }

}