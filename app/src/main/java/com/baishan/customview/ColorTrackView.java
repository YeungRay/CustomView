package com.baishan.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by RayYeung on 2017/2/22.
 */

public class ColorTrackView extends View {

    private Paint mPaint;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;

    private String mText = "杨磊";
    private int mTextSize = sp2px(30);
    private int mTextOriginColor = 0xff000000;
    private int mTextChangeColor = 0xffff0000;
    private float mProgress;
    private int mDirection = DIRECTION_LEFT;

    private int mTextWidth;
    private Rect mTextBounds = new Rect();
    private int mRealWidth;
    private int mTextStartX;


    public ColorTrackView(Context context) {
        this(context, null);
    }

    public ColorTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackView);
        mText = ta.getString(R.styleable.ColorTrackView_text);
        mTextSize = ta.getInt(R.styleable.ColorTrackView_text_size, mTextSize);
        mTextOriginColor = ta.getColor(R.styleable.ColorTrackView_text_origin_color, mTextOriginColor);
        mTextChangeColor = ta.getColor(R.styleable.ColorTrackView_text_change_color, mTextChangeColor);
        mProgress = ta.getFloat(R.styleable.ColorTrackView_progress, 0);
        mDirection = ta.getInt(R.styleable.ColorTrackView_direction, mDirection);
        ta.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        measureText();
    }

    private void measureText() {
        mTextWidth = (int) mPaint.measureText(mText);
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mTextStartX = mRealWidth / 2 - mTextWidth / 2;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = mTextBounds.height();
                break;
        }
        return result + getPaddingTop() + getPaddingBottom();
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = mTextWidth;
                break;
        }
        return result + getPaddingLeft() + getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDirection == DIRECTION_LEFT) {
            drawChangeLeft(canvas);
            drawOriginLeft(canvas);
        } else if (mDirection == DIRECTION_RIGHT) {

        }
    }

    private void drawOriginLeft(Canvas canvas) {
        drawText(canvas, mTextOriginColor, (int) (mTextStartX + mProgress * mTextWidth), mTextStartX + mTextWidth);
    }

    private void drawChangeLeft(Canvas canvas) {
        drawText(canvas, mTextChangeColor, mTextStartX, (int) (mTextStartX + mProgress * mTextWidth));
    }

    private void drawText(Canvas canvas, int color, int startX, int endX) {
        mPaint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(startX, 0, endX, getMeasuredHeight());
        canvas.drawText(mText, mTextStartX, getMeasuredHeight() / 2 + mTextBounds.height() / 2, mPaint);
        canvas.restore();
    }

    private int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }
}
