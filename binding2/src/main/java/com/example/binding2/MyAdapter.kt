package com.example.binding2

class MyAdapter
    (var data: List<TemperatureData>) :
    MyBaseAdapter() {
    override fun getDataAtPosition(position: Int): Any {
        return data[position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        return R.layout.row_layout
    }

    override fun getItemCount(): Int {
        return data.size
    }

}