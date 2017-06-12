package com.mishkaowner.baselibrary.ui.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.mishkaowner.baselibrary.R;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomDatePicker extends DatePicker {
    private Drawable custom_divider = null;
    private int custom_text_color = -1;
    private Unbinder unbinder = null;

    public CustomDatePicker(Context context) {
        super(context);
        if (custom_divider != null && custom_text_color != -1) {
            start();
        }
    }

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        if (custom_divider != null && custom_text_color != -1) {
            start();
        }
    }

    public CustomDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(context, attrs);
        if(custom_divider != null && custom_text_color != -1) {
            start();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(custom_divider != null && custom_text_color != -1) {
            start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void start() {
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this);
        }
        prepareDatePicker();
    }

    public void setAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomDatePicker, 0, 0);
        try {
            custom_divider = ta.getDrawable(R.styleable.CustomDatePicker_custom_divider);
            custom_text_color = ta.getColor(R.styleable.CustomDatePicker_custom_text_color, -1);
        } catch (Exception e) {
            System.out.println("Error on attrb");
        } finally {
            ta.recycle();
        }
    }

    private void prepareDatePicker() {
        try {
            setMaxDate(new Date().getTime());
            updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            LinearLayout llFirst = (LinearLayout) this.getChildAt(0);
            LinearLayout llSecond = (LinearLayout) llFirst.getChildAt(0);
            for (int i = 0; i < llSecond.getChildCount(); i++) {
                NumberPicker picker = (NumberPicker) llSecond.getChildAt(i); // Numberpickers in llSecond
                set_numberpicker_text_colour(picker);
                Field[] pickerFields = NumberPicker.class.getDeclaredFields();
                for (Field pf : pickerFields) {
                    if (pf.getName().equals("mSelectionDivider")) {
                        pf.setAccessible(true);
                        try {
                            pf.set(picker, custom_divider);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (Resources.NotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void set_numberpicker_text_colour(NumberPicker number_picker) {
        final int count = number_picker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = number_picker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                    wheelpaint_field.setAccessible(true);
                    ((Paint) wheelpaint_field.get(number_picker)).setColor(custom_text_color);
                    ((EditText) child).setTextColor(custom_text_color);
                    number_picker.invalidate();
                } catch (NoSuchFieldException e) {
                    System.out.println("Errror " + e.getMessage());
                } catch (IllegalAccessException e) {
                    System.out.println("Errror " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Errror " + e.getMessage());
                }
            }
        }
    }
}