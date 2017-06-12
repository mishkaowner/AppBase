package com.mishkaowner.baselibrary.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.mishkaowner.baselibrary.R;

public class RatioRelativeLayout extends RelativeLayout {
    private static final int _WIDTH = 1, _HEIGHT = 2;
    private int ratio_by = 0;
    private float multiple_of = 0.0f;
    public RatioRelativeLayout(Context context) {
        super(context);
    }
    public RatioRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
    }
    public RatioRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(multiple_of > 0) {
            if (ratio_by == _WIDTH) {
                int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
                int myHeight = (int) (parentWidth * multiple_of);
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(myHeight, MeasureSpec.EXACTLY));
            } else if (ratio_by == _HEIGHT) {
                int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
                int myWidth = (int) (parentHeight * multiple_of);
                super.onMeasure(MeasureSpec.makeMeasureSpec(myWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioRelativeLayout, 0, 0);
        try {
            ratio_by = ta.getInt(R.styleable.RatioRelativeLayout_ratio_by, 0);
            multiple_of = ta.getFloat(R.styleable.RatioRelativeLayout_multiple_of, 0.0f);
        } catch (Exception e) {
            System.out.println("Error on attrb");
        } finally {
            ta.recycle();
        }
    }

    public void setMultipleOf(float multiple_of) {
        this.multiple_of = multiple_of;
        invalidate();
    }
}
