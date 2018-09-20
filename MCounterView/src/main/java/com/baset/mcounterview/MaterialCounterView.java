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
    private ImageView addBtn;
    private ImageView removeBtn;
    private TextView tvCount;
    private long count = 0;
    private int countTextColor;
    private int buttonsDrawableColor;
    private int roundBtnBackgroundColor;
    private Drawable addButtonDrawable;
    private Drawable removeButtonDrawable;
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
        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxCountEnabled){
                  setMaxCount();
                    count++;
                    tvCount.setText(Long.toString(count));
                }else {
                    count++;
                    tvCount.setText(Long.toString(count));
                }
            }
        });
        removeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 0) {
                    count = 0;
                    tvCount.setText(Long.toString(count));
                } else {
                    count--;
                    tvCount.setText(Long.toString(count));
                }
            }
        });
    }

    private void init(@Nullable AttributeSet attributeSet, Context context) {
        inflate(context, R.layout.counter_view, this);
        lnrRoot=findViewById(R.id.lnrRoot);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialCounterView, 0, 0);
        try {
            countTextColor = typedArray.getColor(R.styleable.MaterialCounterView_countTextColor, ContextCompat.getColor(context, android.R.color.black));
            addButtonDrawable = typedArray.getDrawable(R.styleable.MaterialCounterView_addButtonDrawable);
            removeButtonDrawable = typedArray.getDrawable(R.styleable.MaterialCounterView_removeButtonDrawable);
            buttonsDrawableColor = typedArray.getColor(R.styleable.MaterialCounterView_buttonsDrawableColor, ContextCompat.getColor(context, android.R.color.darker_gray));
            roundBtnBackgroundColor = typedArray.getColor(R.styleable.MaterialCounterView_roundBtnBackgroundColor, ContextCompat.getColor(context, android.R.color.darker_gray));

        } finally {
            typedArray.recycle();
        }
        removeBtn = findViewById(R.id.img_remove);
        tvCount = findViewById(R.id.tvCount);
        addBtn = findViewById(R.id.img_add);
        // count_textColor
        tvCount.setTextColor(countTextColor);
        //add&remove btns drawable
        if (addButtonDrawable == null) {
            addBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_gray_24dp));
        } else {
            addBtn.setImageDrawable(addButtonDrawable);
        }
        if (removeButtonDrawable == null) {
            removeBtn.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_remove_gray_24dp));
        } else {
            removeBtn.setImageDrawable(removeButtonDrawable);
        }
        // btns drawable color
        addBtn.setColorFilter(buttonsDrawableColor);
        removeBtn.setColorFilter(buttonsDrawableColor);
        // round btns color
        GradientDrawable add_drawable = (GradientDrawable)addBtn.getBackground();
        add_drawable.setStroke(3, roundBtnBackgroundColor);
        GradientDrawable remove_drawable = (GradientDrawable)removeBtn.getBackground();
        remove_drawable.setStroke(3, roundBtnBackgroundColor);
        GradientDrawable count_drawable = (GradientDrawable)tvCount.getBackground();
        count_drawable.setStroke(3, roundBtnBackgroundColor);
        setupListeners();
    }


    public void setCountTextColor(int color) {
        tvCount.setTextColor(color);
    }

    public void setButtonsDrawableColor(int color) {
        addBtn.setColorFilter(color);
        removeBtn.setColorFilter(color);
    }

    public void setAddButtonDrawable(Drawable drawable) {
        addBtn.setImageDrawable(drawable);
    }

    public void setRemoveButtonDrawable(Drawable drawable) {
        removeBtn.setImageDrawable(drawable);
    }

    public void setRoundBtnsBackgroundColor(int color) {
        GradientDrawable add_drawable = (GradientDrawable)addBtn.getBackground();
        add_drawable.setStroke(3, color);
        GradientDrawable remove_drawable = (GradientDrawable)removeBtn.getBackground();
        remove_drawable.setStroke(3, color);
        GradientDrawable count_drawable = (GradientDrawable)tvCount.getBackground();
        count_drawable.setStroke(3, color);
        setupListeners();
    }

    public void setTypeface(Typeface typeface) {
        tvCount.setTypeface(typeface);
    }

    public void setMaxCount(int maxCount) {
        localMaxCount=maxCount;
        maxCountEnabled=true;
        if (count >= maxCount) {
            count = 0;
            count--;
            tvCount.setText(Long.toString(count));
        }
    }
    private void setMaxCount() {
        if (count >= localMaxCount) {
            count = 0;
            count--;
            tvCount.setText(Long.toString(count));
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
