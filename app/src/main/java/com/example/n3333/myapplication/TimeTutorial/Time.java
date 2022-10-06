package com.example.n3333.myapplication.TimeTutorial;

import android.util.Log;

public class Time {

    private int height;

    public int getHeight() {
        Log.d("Time", "inside getHeight" + height);
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        Log.d("Time", "inside setHeight" + height);
    }
}
