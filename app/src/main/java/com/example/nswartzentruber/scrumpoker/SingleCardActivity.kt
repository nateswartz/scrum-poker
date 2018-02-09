package com.example.nswartzentruber.scrumpoker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.widget.TextView

class SingleCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_card)

        findViewById<TextView>(R.id.card_text).apply {
           text = intent.getStringExtra(EXTRA_NUMBER)
        }

        val card = findViewById<CardView>(R.id.card_view) as CardView
        card.setOnClickListener({ finish() })
    }
}
