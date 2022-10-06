package com.example.n3333.myapplication.TimeTutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n3333.myapplication.R;

public class TimeActivity extends AppCompatActivity {

    private TextView mTvTime;
    private EditText mEdtTime;
    private Button mBtnTime;
    private Time mData = new Time();
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        Log.d("Results", "OnCreate Called " + i);
        mTvTime = findViewById(R.id.tv_time);
        mEdtTime = findViewById(R.id.edt_time);
        mBtnTime = findViewById(R.id.btn_time);

        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String a = mEdtTime.getText().toString();
                    i = Integer.parseInt(a);
                    mData.setHeight(i);
                } catch (NumberFormatException e) {
                    Toast.makeText(TimeActivity.this, "Invalid Value", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//        outState.putInt("values", i);
//        Log.d("Results", "Saved Value: " + i);

        outState.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            i = savedInstanceState.getInt("values");
            mTvTime.setText(""+i);
            Log.d("Results", "Loaded Value: " + i);
        }
    }

    @Override
    protected void onResume() {
        Log.d("Results", "OnResume Called " + i);
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Results", "OnPause Called " + i);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Results", "OnStop Called " + i);
        super.onStop();
    }

}
