package com.example.n3333.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.n3333.myapplication.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding dBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_binding);

        dBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

    }
}
