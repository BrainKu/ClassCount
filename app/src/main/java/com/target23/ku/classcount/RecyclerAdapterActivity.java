package com.target23.ku.classcount;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

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
        List<Object> objectList = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object(),
                new Object(), new Object(), new Object(), new Object(), new Object(),
                new Object(), new Object(), new Object(), new Object(), new Object(),
                new Object(), new Object(), new Object(), new Object(), new Object());
        maxPos = objectList.size();
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new AnimateReAdapter(objectList));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View view = recyclerView.getLayoutManager().getChildAt(0); // 明明取的是第一个，但是返回却是不一样的东西。不过似乎可以根据 getTop 的位置来判断是否对齐
                    if (view != null && view instanceof TextView) {
                        Log.d("RecyclerAdapterActivity", "view.getTop():" + view.getTop() + ",text:" + ((TextView) view).getText());
                    }
                }
            }
        });
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override public void run() {
                mRecyclerView.smoothScrollToPosition(getCurrentPos());
                mHandler.postDelayed(this, 3000);
            }
        }, 3000);
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
