package com.example.n3333.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class GPSLocationActivity extends AppCompatActivity {

    private TextView mTvLat, mTvLong, mTvAltitude, mTvAccuracy, mTvSpeed, mTvSensor, mTvUpdates, mTvAddress;

    private Switch mSwGps,mSwLocationUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocation);
    }

    private void initComponents(){

        mTvLat = findViewById(R.id.tv_lat);
        mTvLong = findViewById(R.id.tv_lon);
        mTvAltitude = findViewById(R.id.tv_altitude);
        mTvAccuracy = findViewById(R.id.tv_accuracy);
        mTvSpeed = findViewById(R.id.tv_speed);
        mTvSensor = findViewById(R.id.tv_sensor);
        mTvUpdates = findViewById(R.id.tv_updates);
        mTvAddress = findViewById(R.id.tv_address);

    }
}