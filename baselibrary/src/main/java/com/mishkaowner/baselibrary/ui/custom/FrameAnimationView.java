package com.mishkaowner.baselibrary.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mishkaowner.baselibrary.R;

import butterknife.ButterKnife;

/**
 * Created by jhkim on 17. 4. 28.
 */

public class FrameAnimationView extends LinearLayout {
    private ImageView aniImage = null;
    private Drawable ani_src = null;

    public FrameAnimationView(Context context) {
        super(context);
        if (ani_src != null) {
            startAnim();
        }
    }

    public FrameAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        if (ani_src != null) {
            startAnim();
        }
    }

    public FrameAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(context, attrs);
        if (ani_src != null) {
            startAnim();
        }
    }

    private void startAnim() {
        inflate(getContext(), R.layout.view_frame_animation, this);
        aniImage = ButterKnife.findById(this, R.id.aniImage);
        if (aniImage != null) {
            aniImage.setImageDrawable(ani_src);
            AnimationDrawable animationDrawable = (AnimationDrawable) aniImage.getDrawable();
            animationDrawable.start();
        }
    }

    public void setAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FrameAnimationView, 0, 0);
        try {
            ani_src = ta.getDrawable(R.styleable.FrameAnimationView_ani_src);
        } catch (Exception e) {
            System.out.println("Error on attrb");
        } finally {
            ta.recycle();
        }
    }
}
