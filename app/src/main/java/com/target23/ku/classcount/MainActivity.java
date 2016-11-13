package com.target23.ku.classcount;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.TextView;

import com.target23.ku.classcount.animation.CameraAnimation;

public class MainActivity extends AppCompatActivity {

    TextView click;
    View animateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateView = findViewById(R.id.v_animate);
        click = (TextView) findViewById(R.id.t_click);
        try {
            Activity a = (Activity) getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        animateView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                v.postDelayed(new Runnable() {
                    @Override public void run() {
                        startActivity(new Intent(v.getContext(), Test2Activity.class));
                    }
                }, 1000);
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                float centerX = v.getWidth() / 2.0f;
                float centerY = v.getHeight() / 2.0f;
                Animation animation = new CameraAnimation(v.getContext(), 0, 180, centerX, centerY, 1000);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                animation.setRepeatMode(Animation.RESTART);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
//                animateView.setAnimation(animation);
                animateView.startAnimation(animation);
                Log.d(TAG, "onClick: " + centerX + "," + centerY);
            }
        });
    }

    public static final String TAG = MainActivity.class.getSimpleName();
}
