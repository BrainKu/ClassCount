package com.target23.ku.classcount.animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhengxinwei@N3072 on 2016/9/19.
 */
public class CameraAnimation extends Animation {

    private float mFromDegree;
    private float mToDegress;
    private float mCenterX;
    private float mCenterY;
    private float mDepthZ;
    private float screenHeight;
    private float screenWidth;

    Camera mCamera = new Camera();

    public CameraAnimation(Context context, float fromDegree, float toDegress, float centerX, float centerY, float depthZ) {
        mFromDegree = fromDegree;
        mToDegress = toDegress;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = -depthZ;
        DisplayMetrics dp = context.getResources().getDisplayMetrics();
        screenHeight = dp.heightPixels;
        screenWidth = dp.widthPixels;
    }

    @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegress = mFromDegree;
        float degress = fromDegress + (mToDegress - fromDegress) * interpolatedTime;
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();
        if (interpolatedTime - 0.5 < 0) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1 - interpolatedTime));
        }
//        camera.rotateY(degress);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
