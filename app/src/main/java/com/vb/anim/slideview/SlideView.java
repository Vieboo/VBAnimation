package com.vb.anim.slideview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.vb.anim.R;

/**
 * Created by Vieboo on 2016/5/30.
 */
public class SlideView extends LinearLayout {

    private Context mContext;
    private boolean hasTimeCount = false;

    private LinearLayout ll_values, ll_time_count;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_silde_view, this);
        ll_values = (LinearLayout) contentView.findViewById(R.id.ll_values);
        ll_time_count = (LinearLayout) contentView.findViewById(R.id.ll_time_count);
    }

    public void setValues(boolean isTimeCount) {
        if(isTimeCount) {
            ll_values.setVisibility(View.GONE);
            ll_time_count.setVisibility(View.VISIBLE);
        }else {
            ll_time_count.setVisibility(View.GONE);
            ll_values.setVisibility(View.VISIBLE);
        }
    }

    public boolean isHasTimeCount() {
        return hasTimeCount;
    }

    public void setHasTimeCount(boolean hasTimeCount) {
        this.hasTimeCount = hasTimeCount;
    }
}
