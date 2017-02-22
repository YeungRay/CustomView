package com.baishan.customview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private ColorTrackView colorTrackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        colorTrackView = (ColorTrackView) findViewById(R.id.colorTrackView);

    }


    public void startLeft(View view){
        ObjectAnimator.ofFloat(colorTrackView,"progress",0,1).setDuration(2000).start();
    }

    public void startRight(View view){

    }
}
