package com.example.n3333.genting_tech_tests.module;

public class SortingRvItem {

    private int mImageResource;
    private String mText1;
    private String mText2;
    private Integer mInteger;

    public SortingRvItem() {

    }

    public SortingRvItem(int imageResource, String text1, String text2, int invalue) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mInteger = invalue;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public Integer getInteger() {
        return mInteger;
    }

    public SortingRvItem setText1(String sText1) {
        this.mText1 = sText1;
        return this;
    }
}
