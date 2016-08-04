package com.example.wanghui.androidstudy.animat;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by wanghui on 2016/8/4.
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int x = (int) ((endValue.x - startValue.x) * fraction);
//        return new Point(x, y);
        return null;
    }
}
