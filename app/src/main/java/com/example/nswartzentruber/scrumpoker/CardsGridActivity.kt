package com.example.nswartzentruber.scrumpoker

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.util.Pair
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ImageView


const val EXTRA_NUMBER = "com.example.nswartzentruber.scrumpoker.NUMBER"
const val EXTRA_CARD_COLOR = "com.example.nswartzentruber.scrumpoker.CARDCOLOR"
const val EXTRA_TEXT_COLOR = "com.example.nswartzentruber.scrumpoker.TEXTCOLOR"

class CardsGridActivity : AppCompatActivity() {

    private var textColor = 0
    private var cardColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_grid)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        postponeEnterTransition()

        val decor = window.decorView
        decor.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                decor.viewTreeObserver.removeOnPreDrawListener(this)
                startPostponedEnterTransition()
                return true
            }
        })

        val gridview = findViewById<GridView>(R.id.card_gridview)
        gridview.adapter = NumberTileAdapter(this)

        gridview.onItemClickListener = OnItemClickListener { _, v, position, id ->
            // Prevent status/nav bar from fading during transition
            val statusBar = findViewById<View>(android.R.id.statusBarBackground)
            val toolbar = findViewById<View>(R.id.my_toolbar)
            val navigationBar = findViewById<View>(android.R.id.navigationBarBackground)
            val statusBarPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME)
            val toolbarPair = Pair.create(toolbar, "transition_toolbar_view")
            val navBarPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME)
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this@CardsGridActivity,
                            Pair.create<View, String>(v, "transition_card_view"),
                            statusBarPair, navBarPair, toolbarPair)
            val intent = Intent(this@CardsGridActivity, SingleCardActivity::class.java).apply {
                putExtra(EXTRA_NUMBER, card_numbers[position])
                putExtra(EXTRA_CARD_COLOR, cardColor)
                putExtra(EXTRA_TEXT_COLOR, textColor)
            }
            startActivity(intent, options.toBundle())
        }

        textColor = Color.argb(255, 0, 0, 0)
        cardColor = ContextCompat.getColor(this ,R.color.colorAccent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.change_color -> {
            when (cardColor) {
                ContextCompat.getColor(this ,R.color.colorAccent) -> {
                    textColor = Color.argb(255, 255, 255, 255)
                    cardColor = ContextCompat.getColor(this, R.color.colorPrimary)
                }
                ContextCompat.getColor(this ,R.color.colorPrimary) -> {
                    textColor = Color.argb(255, 255, 255, 255)
                    cardColor = ContextCompat.getColor(this, R.color.alternateCardColor)
                }
                ContextCompat.getColor(this ,R.color.alternateCardColor) -> {
                    textColor = Color.argb(255, 0, 0, 0)
                    cardColor = ContextCompat.getColor(this, R.color.alternateCardColor2)
                }
                ContextCompat.getColor(this ,R.color.alternateCardColor2) -> {
                    textColor = Color.argb(255, 0, 0, 0)
                    cardColor = ContextCompat.getColor(this, R.color.colorAccent)
                }
            }
            val gridview = findViewById<GridView>(R.id.card_gridview)
            for (i in 0 until gridview.childCount) {
                val child = gridview.getChildAt(i) as CardView
                child.setCardBackgroundColor(cardColor)
                val image = child.findViewById<ImageView>(R.id.card_image)
                image.setColorFilter(textColor)
            }
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}