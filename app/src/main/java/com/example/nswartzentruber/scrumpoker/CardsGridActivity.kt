package com.example.nswartzentruber.scrumpoker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.GridView



class CardsGridActivity : AppCompatActivity() {

    private val numbers = arrayOf("1/2", "1", "2", "3", "5", "8", "13")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_grid)

        val gridview = findViewById<GridView>(R.id.card_gridview)
        gridview.adapter = ArrayAdapter<String>(this, R.layout.number_tile, R.id.card_text, numbers)

        gridview.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, v: View,
                            position: Int, id: Long) {
                Toast.makeText(this@CardsGridActivity, "" + position,
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}