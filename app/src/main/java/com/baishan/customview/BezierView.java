package com.baishan.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by RayYeung on 2016/11/14.
 */

public class BezierView extends View {

    private Path mPath;
    private Paint mPaint;

    private int width;
    private int height;
    private int maxLength;

    private int centerX;
    private int centerY;

    private int radius = 50;
    //圆的控制点与半径的距离
    private float c;
    private float blackMagic = 0.551915024494f;

    private VPoint p1, p3;
    private HPoint p2, p4;

    private float stretchDistance;
    private float mInterpolatedTime;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        stretchDistance = radius;
        c = radius * blackMagic;
        mPath = new Path();
        p1 = new VPoint();
        p3 = new VPoint();
        p2 = new HPoint();
        p4 = new HPoint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        maxLength = width - radius - radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(radius, radius);


    }

    class HPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x) {
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset) {
            top.y = -offset;
            bottom.y = offset;
        }

    }

    class VPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustX(float offset) {
            left.x = -offset;
            right.x = offset;
        }
    }
}
