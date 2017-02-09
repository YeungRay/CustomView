package com.baishan.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RayYeung on 2016/10/17.
 */

public class PieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //数据集
    private List<PieData> mData = new ArrayList<>();
    //起始角度
    private int mStartAngle = 0;
    private int mWidth, mHeight;
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }
        float currentStartAngle = mStartAngle;
        //将画布坐标原点移到中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        //半径
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData data = mData.get(i);
            mPaint.setColor(data.color);
            canvas.drawArc(rect, currentStartAngle, data.angle, true, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(dip2px(getContext(), 15));
            float angle = currentStartAngle + data.angle / 2;
            float x = 1;
            float y = 1;
            float realAng = angle;
            if (angle > 90 && angle < 180) {
                realAng = 180 - angle;
                x = -x;
            } else if (angle > 180 && angle < 270) {
                realAng = angle - 180;
                x = -x;
                y = -y;
            } else if (angle > 270 && angle < 360) {
                realAng = 360 - angle;
                y = -y;
            }
            realAng = (float) (realAng * Math.PI / 180);
            x = x * (float) (r / 2 * Math.cos(realAng));
            y = y * (float) (r / 2 * Math.sin(realAng));
            float w = mPaint.measureText(data.name) / 2;
            canvas.drawText(data.name, x - w, y, mPaint);
            currentStartAngle += data.angle;
        }
    }

    public void setStartAngle(int angle) {
        mStartAngle = angle;
        invalidate();
    }

    public void setData(List<PieData> data) {
        mData = data;
        initData(mData);
        invalidate();
    }

    private void initData(List<PieData> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            PieData pie = data.get(i);
            pie.angle = 360 * pie.percentage;
            pie.color = mColors[i % mColors.length];
        }
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
