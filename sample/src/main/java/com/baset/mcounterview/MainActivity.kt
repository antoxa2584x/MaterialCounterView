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
        val myCounterView:MaterialCounterView=findViewById(R.id.myCounterView)
        //getCount
        myCounterView.count
        //setTypeface
        myCounterView.setTypeface(typeface)
        // change add && remove butn
        myCounterView.setAddButtonDrawable(ContextCompat.getDrawable(baseContext,R.drawable.ic_arrow_forward_black_24dp))
        myCounterView.setRemoveButtonDrawable(ContextCompat.getDrawable(baseContext,R.drawable.ic_arrow_back_black_24dp))
        // change buttons color
        myCounterView.setButtonsDrawableColor(ContextCompat.getColor(baseContext,R.color.colorPrimaryDark))
        // change count value color
        myCounterView.setCountTextColor(ContextCompat.getColor(baseContext,R.color.colorAccent))
        // set limit for counting
        myCounterView.setMaxCount(10)
        // change background stroke color
        myCounterView.setRoundBtnsBackgroundColor(ContextCompat.getColor(baseContext,android.R.color.white))
    }
}
