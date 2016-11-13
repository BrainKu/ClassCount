package com.target23.ku.classcount;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengxinwei@N3072 on 2016/9/23.
 */
public class TouchMoveLayout extends LinearLayout {

    int preX;
    int preY;
    int slop;
    List<TextInfo> mInfoList;

    public TouchMoveLayout(Context context) {
        this(context, null);
    }

    public TouchMoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchMoveLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initThis();
    }

    private void initThis() {
        slop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        setPaintSize(14);
        mInfoList = new ArrayList<>();
    }

    int paintSize;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public void setPaintSize(int sizeInSp) {
        paintSize = sizeInSp;
        mPaint.setTextSize(sizeInSp);
        mPaint.setTextSize(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sizeInSp, getContext().getResources().getDisplayMetrics()));
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                preX = (int) event.getX();
                preY = (int) event.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                int curX = (int) event.getX();
                int step = curX - preX;
                onChildMove(step);
                preX = curX;
                break;
        }
        return true;
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
//        if (getChildCount() > 0) {
//            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override public void onGlobalLayout() {
//                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    View view = getChildAt(0);
//                    view.scrollBy(-getMeasuredWidth(), 0);
//                    Log.d("TouchMoveLayout", "getMeasuredWidth():" + getMeasuredWidth());
//                }
//            });
//        }
//        postDelayed(mRunnable, 1000);
        mChoreographer.postFrameCallbackDelayed(mFrameCallback, 1000);
    }

    Choreographer.FrameCallback mFrameCallback = new Choreographer.FrameCallback() {
        @Override public void doFrame(long frameTimeNanos) {
            View child = getChildAt(0);
            child.scrollBy(3, 0);
            Log.d("TouchMoveLayout", "child.getScrollX():" + child.getScrollX());
            Log.d("TouchMoveLayout", "child.getWidth():" + child.getWidth());
            if (getChildAt(0).getScrollX() <= getChildAt(0).getWidth()) {
                mChoreographer.postFrameCallback(this);
            }
        }
    };

    Choreographer mChoreographer = Choreographer.getInstance();

    Runnable mRunnable = new Runnable() {
        @Override public void run() {
            onChildMove(-3);
            postDelayed(this, 40);
        }
    };

    public void addText(CharSequence text) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(paintSize);
        textView.setText(text);
        float length = mPaint.measureText(text, 0, text.length());
    }

    public void addText(TextView textView) {
        textView.setTextSize(paintSize);
        float len = mPaint.measureText(textView.getText(), 0, textView.getText().length());
    }

    static class TextInfo {
        TextView mView;
        Animator mAnimator;
        int length;

        public void init() {
//            mChoreographer.postFrameCallback();
        }
    }

    private void onChildMove(int moveX) {
        int count = getChildCount();
        if (count > 0) {
            View view = getChildAt(0);
            view.scrollBy(-moveX, 0);
        }
    }
}
