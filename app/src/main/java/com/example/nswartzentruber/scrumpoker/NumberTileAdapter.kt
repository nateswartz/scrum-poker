package com.example.nswartzentruber.scrumpoker

import android.content.Context
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView

val card_numbers = arrayOf(R.drawable.half, R.drawable.one, R.drawable.two,
        R.drawable.three, R.drawable.five, R.drawable.eight, R.drawable.thirteen)

class NumberTileAdapter(private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return card_numbers.size
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
        val cardImageView = convertView!!.findViewById<ImageView>(R.id.card_image)
        // Populate the data into the template view using the data object
        cardImageView.setImageResource(card_numbers[position])
        return convertView!!
    }
}