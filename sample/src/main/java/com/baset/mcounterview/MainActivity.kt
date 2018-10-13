package com.baset.mcounterview

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterView: MaterialCounterView = findViewById(R.id.myCounterView)
        val colorAccent = ContextCompat.getColor(this, R.color.colorAccent)

        counterView.apply {
            //getCount
            count
            //setTypeface
            setTypeface(Typeface.createFromAsset(assets, "font.ttf"))
            // change add && remove butn
            setIncreaseButtonDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_arrow_forward_black_24dp)!!)
            setDecreaseButtonDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_arrow_back_black_24dp)!!)
            // change buttons color
            setIncreaseDecreaseDrawableColor(colorAccent)
            // change count value color
            setCounterTextColor(colorAccent)
            // set limit for counting
            setMaxLimit(8)
            // change background stroke color
            setBorderBtnsBackgroundColor(colorAccent)
        }
    }
}
