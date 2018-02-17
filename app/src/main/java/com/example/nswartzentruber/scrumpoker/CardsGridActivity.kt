package com.example.nswartzentruber.scrumpoker

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView


const val EXTRA_NUMBER = "com.example.nswartzentruber.scrumpoker.NUMBER"

class CardsGridActivity : AppCompatActivity() {

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
            }
            startActivity(intent, options.toBundle())
        }
    }
}