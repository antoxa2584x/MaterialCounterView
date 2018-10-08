package com.baset.mcounterview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MaterialCounterView extends LinearLayout {
    private ImageView increaseBtn;
    private ImageView decreaseBtn;
    private TextView tvCount;
    private int count = 0;
    private int countTextColor;
    private int IncreaseDecreaseDrawableColor;
    private int borderBtnBackgroundColor;
    private Drawable increaseButtonDrawable;
    private Drawable decreaseButtonDrawable;
    private LinearLayout lnrRoot;
    private boolean maxCountEnabled=false;
    private int localMaxCount;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public MaterialCounterView(Context context) {
        super(context);
        init(null, context);
    }

    public MaterialCounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);

    }

    public MaterialCounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    public MaterialCounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, context);
    }

    private void setupListeners() {
        increaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxCountEnabled){
                    setMaxLimit();
                    count++;
                    tvCount.setText(Integer.toString(count));
                }else {
                    count++;
                    tvCount.setText(Integer.toString(count));
                }
            }
        });
        decreaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 0) {
                    count = 0;
                    tvCount.setText(Integer.toString(count));
                } else {
                    count--;
                    tvCount.setText(Integer.toString(count));
                }
            }
        });
    }

    private void init(@Nullable AttributeSet attributeSet, Context context) {
        inflate(context, R.layout.counter_view, this);
        lnrRoot=findViewById(R.id.lnrRoot);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialCounterView, 0, 0);
        try {
            countTextColor = typedArray.getColor(R.styleable.MaterialCounterView_counterTextColor, ContextCompat.getColor(context, android.R.color.black));
            increaseButtonDrawable = typedArray.getDrawable(R.styleable.MaterialCounterView_increaseButtonDrawable);
            decreaseButtonDrawable = typedArray.getDrawable(R.styleable.MaterialCounterView_decreaseButtonDrawable);
            IncreaseDecreaseDrawableColor = typedArray.getColor(R.styleable.MaterialCounterView_increase_decreaseDrawableColor, ContextCompat.getColor(context, android.R.color.darker_gray));
            borderBtnBackgroundColor = typedArray.getColor(R.styleable.MaterialCounterView_borderBtnBackgroundColor, ContextCompat.getColor(context, android.R.color.darker_gray));

        } finally {
            typedArray.recycle();
        }
        decreaseBtn = findViewById(R.id.img_remove);
        tvCount = findViewById(R.id.tvCount);
        increaseBtn = findViewById(R.id.img_add);
        // count_textColor
        tvCount.setTextColor(countTextColor);
        //add&remove btns drawable
        if (increaseButtonDrawable == null) {
            increaseBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_gray_24dp));
        } else {
            increaseBtn.setImageDrawable(increaseButtonDrawable);
        }
        if (decreaseButtonDrawable == null) {
            decreaseBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_remove_gray_24dp));
        } else {
            decreaseBtn.setImageDrawable(decreaseButtonDrawable);
        }
        // btns drawable color
        increaseBtn.setColorFilter(IncreaseDecreaseDrawableColor);
        decreaseBtn.setColorFilter(IncreaseDecreaseDrawableColor);
        // round btns color
        GradientDrawable add_drawable = (GradientDrawable) increaseBtn.getBackground();
        add_drawable.setStroke(3, borderBtnBackgroundColor);
        GradientDrawable remove_drawable = (GradientDrawable) decreaseBtn.getBackground();
        remove_drawable.setStroke(3, borderBtnBackgroundColor);
        GradientDrawable count_drawable = (GradientDrawable)tvCount.getBackground();
        count_drawable.setStroke(3, borderBtnBackgroundColor);
        setupListeners();
    }

    public void setOnCounterButtonsClick(final OnCounterButtonsClick onCounterButtonsClick) {
        increaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxCountEnabled) {
                    setMaxLimit();
                    count++;
                    tvCount.setText(Integer.toString(count));
                } else {
                    count++;
                    tvCount.setText(Integer.toString(count));
                }
                onCounterButtonsClick.onIncreaseClick(count);
            }
        });
        decreaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 0) {
                    count = 0;
                    tvCount.setText(Integer.toString(count));
                } else {
                    count--;
                    tvCount.setText(Integer.toString(count));
                }
                onCounterButtonsClick.onDecreaseClick(count);
            }
        });
    }

    public void setCounterTextColor(int color) {
        tvCount.setTextColor(color);
    }

    public void setIncreaseDecreaseDrawableColor(int color) {
        increaseBtn.setColorFilter(color);
        decreaseBtn.setColorFilter(color);
    }

    public void setIncreaseButtonDrawable(Drawable drawable) {
        increaseBtn.setImageDrawable(drawable);
    }

    public void setDecreaseButtonDrawable(Drawable drawable) {
        decreaseBtn.setImageDrawable(drawable);
    }

    public void setBorderBtnsBackgroundColor(int color) {
        GradientDrawable add_drawable = (GradientDrawable) increaseBtn.getBackground();
        add_drawable.setStroke(3, color);
        GradientDrawable remove_drawable = (GradientDrawable) decreaseBtn.getBackground();
        remove_drawable.setStroke(3, color);
        GradientDrawable count_drawable = (GradientDrawable)tvCount.getBackground();
        count_drawable.setStroke(3, color);
        setupListeners();
    }

    public void setTypeface(Typeface typeface) {
        tvCount.setTypeface(typeface);
    }

    public void setMaxLimit(int maxCount) {
        localMaxCount=maxCount;
        maxCountEnabled=true;
        if (count >= maxCount) {
            count = 0;
            count--;
            tvCount.setText(Integer.toString(count));
        }
    }

    private void setMaxLimit() {
        if (count >= localMaxCount) {
            count = 0;
            count--;
            tvCount.setText(Integer.toString(count));
        }
    }
    @Override
    public void setOrientation(int orientation) {
        lnrRoot.setOrientation(orientation);
    }
    public long getCount(){
        return count;
    }
}
