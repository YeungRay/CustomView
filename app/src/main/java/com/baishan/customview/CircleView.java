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

public class CircleView extends View {

    private int radius = 100;
    private Paint mPaint;
    private Path mPath;

    private int centerX, centerY;

    private VPoint p1, p3;
    private HPoint p2, p4;

    private float c;
    private float blackMagic = 0.551915024494f;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
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
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);

        p1.setY(-radius);
        p3.setY(radius);
        p1.adjustX(c);
        p3.adjustX(c);
        p2.setX(radius+radius);
        p4.setX(-radius);
        p2.adjustY(c);
        p4.adjustY(c);


        mPath.moveTo(p1.x,p1.y);
        mPath.cubicTo(p1.right.x,p1.right.y,p2.top.x,p2.top.y,p2.x,p2.y);
        mPath.cubicTo(p2.bottom.x,p2.bottom.y,p3.right.x,p3.right.y,p3.x,p3.y);
        mPath.cubicTo(p3.left.x,p3.left.y,p4.bottom.x,p4.bottom.y,p4.x,p4.y);
        mPath.cubicTo(p4.top.x,p4.top.y,p1.left.x,p1.left.y,p1.x,p1.y);
        canvas.drawPath(mPath,mPaint);
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

        public void adjustY(float offset){
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

        public void adjustX(float offset){
            left.x = -offset;
            right.x = offset;
        }
    }
}
