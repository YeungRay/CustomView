package com.baishan.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by RayYeung on 2016/12/14.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0 && isTouchEvent&&listener!=null) {
            listener.scroll(Math.abs(deltaY));
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    private OnScrollListener listener;
    public void setOnScrollListener(OnScrollListener listener){
        this.listener = listener;
    }
    public interface OnScrollListener {
        void scroll(int deltaY);
        void release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_CANCEL||ev.getAction()==MotionEvent.ACTION_UP){
            if(listener!=null)listener.release();
        }
        return super.onTouchEvent(ev);
    }
}
