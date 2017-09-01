package com.baishan.customview;

import android.content.Context;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/3/28.
 */

public class SpringScrollView extends NestedScrollView {


    private float startDragY = 0;
    private SpringAnimation springAnim;


    public SpringScrollView(Context context) {
        this(context, null);
    }

    public SpringScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        springAnim = new SpringAnimation(this, DynamicAnimation.TRANSLATION_Y, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (getScrollY() <= 0) {//顶部向下拉动
                    if (startDragY == 0) {
                        startDragY = ev.getRawY();
                    }
                    if (ev.getRawY() - startDragY >= 0) {
                        setTranslationY((ev.getRawY() - startDragY) /3);
                        return true;
                    } else {
                        startDragY = 0;
                        springAnim.cancel();
                        setTranslationY(0);
                    }
                } else if (getScrollY() + getHeight() >= getChildAt(0).getMeasuredHeight()) {
                    if(startDragY==0){
                        startDragY = ev.getRawY();
                    }
                    if (ev.getRawY() - startDragY <= 0) {
                        setTranslationY((ev.getRawY() - startDragY) / 3);
                        return true;
                    }else{
                        startDragY = 0;
                        springAnim.cancel();
                        setTranslationY(0);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (getTranslationY() != 0) {
                    springAnim.start();
                }
                startDragY = 0;
                break;
        }
        return super.onTouchEvent(ev);
    }
}
