package com.target23.ku.classcount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by zhengxinwei@N3072 on 2016/8/30.
 */
public class OuterLayout extends LinearLayout {
    private boolean mReadyForPull;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mInitialMotionX;
    private float mInitialMotionY;

    private float mTouchSlop;
    private boolean isDragged = false;

    public OuterLayout(Context context) {
        this(context, null);
    }

    public OuterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OuterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            isDragged = false;
            return false;
        }
        if (action != MotionEvent.ACTION_DOWN && isDragged) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                if (isReadyForPull()) {
                    final float x = ev.getX(), y = ev.getY();
                    final float yDiff, xDiff, absYDiff;
                    yDiff = y - mLastMotionY;
                    xDiff = x - mLastMotionX;
                    absYDiff = Math.abs(yDiff);
                    if (absYDiff > mTouchSlop && absYDiff > Math.abs(xDiff)) {
                        mLastMotionY = y;
                        mLastMotionX = x;
                        isDragged = true;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (isReadyForPull()) {
                    mLastMotionX = mInitialMotionX = ev.getX();
                    mLastMotionY = mInitialMotionY = ev.getY();
                    isDragged = false;
                }
                break;
        }
        return isDragged;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                if (isDragged) {
                    mLastMotionX = event.getX();
                    mLastMotionY = event.getY();
                    pullEvent();
                    return true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (isReadyForPull()) {
                    mLastMotionX = mInitialMotionX = event.getX();
                    mLastMotionY = mInitialMotionY = event.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isDragged) {
                    isDragged = false;
                    smoothScrollTo(0);
                }
                return true;
        }
        return false;
    }

    private void smoothScrollTo(int scroll) {
        setHeaderScroll(1);
    }

    private void setHeaderScroll(int distance) {
        scrollTo(0, distance);
    }

    private void pullEvent() {

    }

    public boolean isReadyForPull() {
        return true;
    }
}
