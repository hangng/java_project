package com.example.n3333.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    EditText edt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        edt = findViewById(R.id.edt);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myTask myTask = new myTask(AsyncTaskActivity.this, tv, btn);
        myTask.execute();
        btn.setEnabled(false);
        btn.setAllCaps(false);

    }
}
