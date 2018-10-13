package com.baset.mcounterview

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class MaterialCounterView : LinearLayout {

    private lateinit var lnrRoot: LinearLayout
    private lateinit var increaseBtn: AppCompatImageView
    private lateinit var decreaseBtn: AppCompatImageView
    private lateinit var tvCount: AppCompatTextView

    private lateinit var countDrawable: GradientDrawable
    private lateinit var addDrawable: GradientDrawable
    private lateinit var removeDrawable: GradientDrawable

    private var increaseButtonDrawable: Drawable? = null
    private var decreaseButtonDrawable: Drawable? = null

    private var countTextColor = 0
    private var increaseDecreaseDrawableColor = 0
    private var borderBtnBackgroundColor = 0

    private var localMaxCount = 0

    var count = 0

    private var maxCountEnabled = false

    constructor(context: Context) : super(context) {
        init(null, context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, context)

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, context)
    }

    private fun setupListeners() {
        increaseBtn.setOnClickListener {
            if (maxCountEnabled) {
                setMaxLimit()
                count++
                tvCount.text = count.toString()
            } else {
                count++
                tvCount.text = count.toString()
            }
        }
        decreaseBtn.setOnClickListener {
            if (count <= 0) {
                count = 0
                tvCount.text = count.toString()
            } else {
                count--
                tvCount.text = count.toString()
            }
        }
    }


    private fun init(attributeSet: AttributeSet?, context: Context) {
        View.inflate(context, R.layout.counter_view, this)

        with(context.obtainStyledAttributes(attributeSet, R.styleable.MaterialCounterView, 0, 0)) {
            try {
                countTextColor = getColor(R.styleable.MaterialCounterView_counterTextColor,
                        ContextCompat.getColor(context, android.R.color.black))
                increaseButtonDrawable = getDrawable(R.styleable.MaterialCounterView_increaseButtonDrawable)
                decreaseButtonDrawable = getDrawable(R.styleable.MaterialCounterView_decreaseButtonDrawable)
                increaseDecreaseDrawableColor = getColor(R.styleable.MaterialCounterView_increase_decreaseDrawableColor,
                        ContextCompat.getColor(context, android.R.color.darker_gray))
                borderBtnBackgroundColor = getColor(R.styleable.MaterialCounterView_borderBtnBackgroundColor,
                        ContextCompat.getColor(context, android.R.color.darker_gray))

            } finally {
                recycle()
            }
        }

        lnrRoot = findViewById(R.id.lnrRoot)
        decreaseBtn = findViewById(R.id.img_remove)
        tvCount = findViewById(R.id.tvCount)
        increaseBtn = findViewById(R.id.img_add)

        // count_textColor
        tvCount.setTextColor(countTextColor)

        //add&remove btns drawable
        increaseButtonDrawable?.let {
            increaseBtn.setImageDrawable(it)
        } ?: run {
            increaseBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_gray_24dp))
        }

        decreaseButtonDrawable?.let {
            decreaseBtn.setImageDrawable(it)
        } ?: run {
            decreaseBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_remove_gray_24dp))
        }

        // btns drawable color
        with(increaseDecreaseDrawableColor) {
            increaseBtn.setColorFilter(this)
            decreaseBtn.setColorFilter(this)
        }

        // round btns color
        addDrawable = (increaseBtn.background as GradientDrawable).apply {
            setStroke(3, borderBtnBackgroundColor)
        }
        removeDrawable = (decreaseBtn.background as GradientDrawable).apply {
            setStroke(3, borderBtnBackgroundColor)
        }
        countDrawable = (tvCount.background as GradientDrawable).apply {
            setStroke(3, borderBtnBackgroundColor)
        }

        setupListeners()
    }

    fun setOnCounterButtonsClick(onCounterButtonsClick: OnCounterButtonsClick) {
        increaseBtn.setOnClickListener {
            if (maxCountEnabled) {
                setMaxLimit()
                count++
                tvCount.text = count.toString()
            } else {
                count++
                tvCount.text = count.toString()
            }
            onCounterButtonsClick.onIncreaseClick(count)
        }
        decreaseBtn.setOnClickListener {
            if (count <= 0) {
                count = 0
                tvCount.text = count.toString()
            } else {
                count--
                tvCount.text = count.toString()
            }
            onCounterButtonsClick.onDecreaseClick(count)
        }
    }

    fun setCounterTextColor(color: Int) {
        tvCount.setTextColor(color)
    }

    fun setIncreaseDecreaseDrawableColor(color: Int) {
        increaseBtn.setColorFilter(color)
        decreaseBtn.setColorFilter(color)
    }

    fun setIncreaseButtonDrawable(drawable: Drawable) {
        increaseBtn.setImageDrawable(drawable)
    }

    fun setDecreaseButtonDrawable(drawable: Drawable) {
        decreaseBtn.setImageDrawable(drawable)
    }

    fun setBorderBtnsBackgroundColor(color: Int) {
        addDrawable = (increaseBtn.background as GradientDrawable).apply {
            setStroke(3, color)
        }
        removeDrawable = (decreaseBtn.background as GradientDrawable).apply {
            setStroke(3, color)
        }
        countDrawable = (tvCount.background as GradientDrawable).apply {
            setStroke(3, color)
        }

        setupListeners()
    }

    fun setTypeface(typeface: Typeface) {
        tvCount.typeface = typeface
    }

    fun setMaxLimit(maxCount: Int) {
        localMaxCount = maxCount
        maxCountEnabled = true

        if (count >= maxCount) {
            count = 0
            count--
            tvCount.text = count.toString()
        }
    }

    private fun setMaxLimit() {
        if (count >= localMaxCount) {
            count = 0
            count--
            tvCount.text = count.toString()
        }
    }

    override fun setOrientation(orientation: Int) {
        lnrRoot.orientation = orientation
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
