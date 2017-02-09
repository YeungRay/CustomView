package com.baishan.customview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by RayYeung on 2016/12/15.
 */

public class ScrollingParent extends LinearLayout implements NestedScrollingParent {
    private  LinearLayout llContent;
    private  LinearLayout container;
    private NestedScrollView scrollView;
    private ImageView image;
    private int imgHeight;
    private Scroller mScroller;

    private static final String TAG = "ScrollingParent";


    public ScrollingParent(Context context) {
        this(context, null);
    }

    public ScrollingParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "Constructor");
        View.inflate(context, R.layout.activity_main1, this);
        image = (ImageView) findViewById(R.id.image);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        llContent = (LinearLayout) findViewById(R.id.llContent);
//        container = (LinearLayout) findViewById(R.id.container);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged");
        imgHeight = image.getMeasuredHeight();
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.e(TAG, "onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll");
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedFling");
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.e(TAG, "onNestedPreFling");
        return true;
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll");
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.e(TAG, "onNestedPreScroll");
        boolean hideTop = dy > 0 && getScrollY() < imgHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);
        if (showTop || hideTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
//        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
//        params.height = getMeasuredHeight();
//        scrollView.setLayoutParams(params);
//        System.out.println("imgHeight--->"+imgHeight);
//        System.out.println(getMeasuredWidth()+"---"+getMeasuredHeight());
//        setMeasuredDimension(getMeasuredWidth(), imgHeight + getMeasuredHeight());
//        System.out.println(imgHeight + getMeasuredHeight());
        ViewGroup.LayoutParams params = llContent.getLayoutParams();
        params.height = imgHeight+getMeasuredHeight();
        llContent.setLayoutParams(params);
    }


    //    @Override
//    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
//        ViewGroup.LayoutParams lp = child.getLayoutParams();
//
//        int childWidthMeasureSpec;
//        int childHeightMeasureSpec;
//
//        childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), lp.width);
//
//        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//
//        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//    }
//
//    @Override
//    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec,
//                                           int heightUsed) {
//        Log.e(TAG, "measureChildWithMargins");
//        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//
//        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin
//                + lp.rightMargin + widthUsed, lp.width);
//        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED);
//
//        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//    }


    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
