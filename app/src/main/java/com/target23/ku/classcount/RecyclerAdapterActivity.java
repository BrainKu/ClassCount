package com.target23.ku.classcount;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecyclerAdapterActivity extends AppCompatActivity {

    int curPos = 0;
    int maxPos = 0;
    RecyclerView mRecyclerView;
    private int mCurrentPos;

    private int interval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_down);
        mRecyclerView = (RecyclerView) findViewById(R.id.add_banner);
        List<Object> objectList = Arrays.asList(new Object(), new Object(), new Object(), new Object(),
                new Object(), new Object(), new Object(), new Object(), new Object());
        maxPos = objectList.size();
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new AnimateReAdapter(objectList));
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override public void run() {
                mRecyclerView.smoothScrollToPosition(getCurrentPos());
                mHandler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    Handler mHandler = new Handler();

    public int getCurrentPos() {
        if (curPos >= maxPos) {
            curPos = curPos - maxPos;
        } else {
            curPos++;
        }
        return curPos;
    }
}
