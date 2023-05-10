package com.practice.genting_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvLogIn;
    private EditText mEtUserName, mEtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mTvLogIn = findViewById(R.id.tv_log_in);
        mEtUserName = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);

        mTvLogIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }
}