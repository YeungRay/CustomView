package com.baishan.customview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PieData> mData = new ArrayList<>();
    private int height;
    private int totalNum = 0;
    private int paddingTop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PieView pieView = (PieView) findViewById(R.id.pieView);
//        mData.add(new PieData("Android", 0.4f));
//        mData.add(new PieData("Adsad", 0.2f));
//        mData.add(new PieData("IOS", 0.2f));
//        mData.add(new PieData("WindowPhone", 0.2f));
//        pieView.setData(mData);
        final MyScrollView scrollView = (MyScrollView) findViewById(R.id.scrollView);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                height = imageView.getHeight();
                System.out.println("height-->" + height);
            }
        });
        scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void scroll(int deltaY) {
//                if (totalNum > 100) {
                    paddingTop += deltaY / 3;
                    scrollView.setPadding(0, paddingTop, 0, 0);
//                } else {
//                    imageView.getLayoutParams().height += deltaY / 2;
//                    imageView.requestLayout();
//                    totalNum += deltaY / 3;
//                }
            }

            @Override
            public void release() {
//                totalNum = 0;
//                paddingTop = 0;
//                int curHeight = imageView.getHeight();
//                System.out.println("current height-->" + curHeight);
                ValueAnimator animator = ValueAnimator.ofInt(paddingTop, 0);
                animator.setDuration(300);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        scrollView.setPadding(0, (Integer) animation.getAnimatedValue(), 0, 0);
//                        imageView.getLayoutParams().height = (int) animation.getAnimatedValue();
//                        imageView.requestLayout();
                    }
                });
                //animator.setInterpolator(new OvershootInterpolator());
                animator.start();
                paddingTop = 0;
            }
        });
    }
}
