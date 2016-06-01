package com.vb.anim.slideview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

/**
 * Created by Vieboo on 2016/5/30.
 */
public class SlideLayout extends FrameLayout {

    private Context mContext;
    private SlideView slide1, slide2;
    private int height;
    private ObjectAnimator animIn1, animOut1, animIn2, animOut2;
    private int currentShow = 1;

    public SlideLayout(Context context) {
        super(context);
        init();
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        slide1 = new SlideView(mContext);
        slide2 = new SlideView(mContext);
        this.addView(slide2);
        this.addView(slide1);

        height = getViewHeight(slide1);

        animIn1 = ObjectAnimator.ofFloat(slide1, "translationY", height, 0);
        animIn1.setDuration(1000);
        animIn1.setTarget(slide1);
        animIn1.addListener(showListener1);

        animOut1 = ObjectAnimator.ofFloat(slide1, "translationY", 0, -height);
        animOut1.setDuration(1000);
        animOut1.setTarget(slide1);

        animIn2 = ObjectAnimator.ofFloat(slide2, "translationY", height, 0);
        animIn2.setDuration(1000);
        animIn2.setTarget(slide2);
        animIn2.addListener(showListener2);

        animOut2 = ObjectAnimator.ofFloat(slide2, "translationY", 0, -height);
        animOut2.setDuration(1000);
        animOut2.setTarget(slide2);

        mHandler.postDelayed(run, 5000);
    }

    Animator.AnimatorListener showListener1 = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            slide1.setValues(false);
        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    Animator.AnimatorListener showListener2 = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            slide2.setValues(true);
        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(1 == currentShow) {
                currentShow = 2;
                animOut1.start();
                animIn2.start();
            }else {
                currentShow = 1;
                animOut2.start();
                animIn1.start();
            }
        }
    };

    Runnable run = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            mHandler.postDelayed(run, 5000);
        }
    };

    /*
     * 获取控件高
     */
    public static int getViewHeight(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

}
