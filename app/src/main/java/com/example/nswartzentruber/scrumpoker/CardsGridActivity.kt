package com.example.nswartzentruber.scrumpoker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView

const val EXTRA_NUMBER = "com.example.nswartzentruber.scrumpoker.NUMBER"

class CardsGridActivity : AppCompatActivity() {

    private val numbers = arrayOf("1/2", "1", "2", "3", "5", "8", "13")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_grid)

        val gridview = findViewById<GridView>(R.id.card_gridview)
        gridview.adapter = NumberTileAdapter(this)

        gridview.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, v: View,
                            position: Int, id: Long) {
                val intent = Intent(this@CardsGridActivity, SingleCardActivity::class.java).apply {
                    putExtra(EXTRA_NUMBER, numbers[position])
                }
                startActivity(intent)
            }
        }
    }
}