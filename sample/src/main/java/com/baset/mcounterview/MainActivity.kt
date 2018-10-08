package com.baset.mcounterview

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat

class MainActivity : AppCompatActivity() {
var typeface:Typeface?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        typeface= Typeface.createFromAsset(assets,"font.ttf")
        var counterView: MaterialCounterView = findViewById(R.id.myCounterView)
        //getCount
        counterView.count
        //setTypeface
        counterView.setTypeface(typeface)
        // change add && remove butn
        counterView.setIncreaseButtonDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_arrow_forward_black_24dp))
        counterView.setDecreaseButtonDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_arrow_back_black_24dp))
        // change buttons color
        counterView.setIncreaseDecreaseDrawableColor(ContextCompat.getColor(baseContext, R.color.colorAccent))
        // change count value color
        counterView.setCounterTextColor(ContextCompat.getColor(baseContext, R.color.colorAccent))
        // set limit for counting
        counterView.setMaxLimit(8)
        // change background stroke color
        counterView.setBorderBtnsBackgroundColor(ContextCompat.getColor(baseContext, R.color.colorAccent))

    }
}
