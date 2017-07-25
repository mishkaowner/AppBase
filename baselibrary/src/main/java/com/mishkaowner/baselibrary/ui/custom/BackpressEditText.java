package com.mishkaowner.baselibrary.ui.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class BackpressEditText extends AppCompatEditText {
    private BackpressEditTextListener listener = null;

    public BackpressEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public BackpressEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if(listener != null) {
                listener.onBackPressed();
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setListener(BackpressEditTextListener listener) {
        this.listener = listener;
    }
}
