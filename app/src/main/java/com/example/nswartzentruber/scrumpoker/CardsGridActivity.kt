package com.example.nswartzentruber.scrumpoker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.app.ActivityOptions
import android.util.Pair


const val EXTRA_NUMBER = "com.example.nswartzentruber.scrumpoker.NUMBER"

class CardsGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_grid)

        val gridview = findViewById<GridView>(R.id.card_gridview)
        gridview.adapter = NumberTileAdapter(this)

        gridview.onItemClickListener = OnItemClickListener { _, v, position, id ->
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this@CardsGridActivity,
                            Pair.create<View, String>(v, "transition_card_view"))//,
                            //Pair.create<View, String>(v.findViewById<ImageView>(R.id.card_image), "transition_card_image"))
            val intent = Intent(this@CardsGridActivity, SingleCardActivity::class.java).apply {
                putExtra(EXTRA_NUMBER, card_numbers[position])
            }
            startActivity(intent, options.toBundle())
        }
    }
}