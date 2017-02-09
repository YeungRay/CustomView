package com.baishan.customview;

/**
 * Created by RayYeung on 2016/10/17.
 */

public class PieData {

    public String name;
    public float percentage;

    public int color = 0;
    public float angle = 0;

    public PieData(String name, float percentage) {
        this.name = name;
        this.percentage = percentage;
    }
}
