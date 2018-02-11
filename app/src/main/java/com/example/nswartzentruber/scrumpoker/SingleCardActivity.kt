package com.example.nswartzentruber.scrumpoker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.widget.ImageView

class SingleCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_card)

        findViewById<ImageView>(R.id.card_image).apply {
           setImageResource(intent.getIntExtra(EXTRA_NUMBER, 0))
        }

        val card = findViewById<CardView>(R.id.card_view) as CardView
        card.setOnClickListener({ finishAfterTransition() })
    }
}
