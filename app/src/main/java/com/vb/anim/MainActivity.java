package com.vb.anim;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout silde_layout;
    private LinearLayout silde_layout2;
    private Button btn_start, btn_stop;
    private int slideHeight;
    private boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        silde_layout = (LinearLayout) findViewById(R.id.silde_layout);
        silde_layout2 = (LinearLayout) findViewById(R.id.silde_layout2);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        slideHeight = getViewHeight(silde_layout);

        final ObjectAnimator show = ObjectAnimator.ofFloat(silde_layout, "translationY", slideHeight, 0);
        show.setDuration(1000);
        show.setTarget(silde_layout);

        final ObjectAnimator out = ObjectAnimator.ofFloat(silde_layout, "translationY", 0, -slideHeight);
        out.setDuration(1000);
        out.setTarget(silde_layout);

        final ObjectAnimator show1 = ObjectAnimator.ofFloat(silde_layout2, "translationY", slideHeight, 0);
        show1.setDuration(1000);
        show1.setTarget(silde_layout2);

        final ObjectAnimator out1 = ObjectAnimator.ofFloat(silde_layout2, "translationY", 0, -slideHeight);
        out1.setDuration(1000);
        out1.setTarget(silde_layout2);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!b) {
                    out1.start();
                    show.start();
                }else {
                    out.start();
                    show1.start();
                }
                b = !b;
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(out1.isRunning()) out1.cancel();
                if(show.isRunning()) show.cancel();
                if(out.isRunning()) out.cancel();
                if(show1.isRunning()) show1.cancel();
            }
        });

    }

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