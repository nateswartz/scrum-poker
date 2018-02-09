package com.example.nswartzentruber.scrumpoker

import android.widget.TextView
import android.content.Context
import android.view.*
import android.widget.BaseAdapter

class NumberTileAdapter(private val context: Context) : BaseAdapter() {

    private val numbers = arrayOf("1/2", "1", "2", "3", "5", "8", "13")

    override fun getCount(): Int {
        return numbers.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // Get the data item for this position
        val user = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.number_tile, parent, false)
        }
        // Lookup view for data population
        val cardTextView = convertView!!.findViewById<TextView>(R.id.card_text)
        // Populate the data into the template view using the data object
        cardTextView.text = numbers[position]
        return convertView
    }
}