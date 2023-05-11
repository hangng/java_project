package com.example.n3333.practice;

public class DataHelper {

    private String a;

    public static String[] title = new String[]{
            "Bee Mad", "Bee Sad", "Bee Happy"
    };


    public static int[] picturePath = new int[]{
            R.drawable.mad,
            R.drawable.sad,
            R.drawable.happy
    };

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
