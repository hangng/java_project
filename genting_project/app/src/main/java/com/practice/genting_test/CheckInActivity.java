package com.practice.genting_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtDescp;
    private ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_activity);

        mEtDescp = findViewById(R.id.et_description);
        mIvPhoto = findViewById(R.id.iv_photo);
        mIvPhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}