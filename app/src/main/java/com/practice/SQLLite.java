package com.example.n3333.genting_tech_tests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.n3333.genting_tech_tests.datahelper.MyDatabaseHelper;

public class SQLLite extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;

    private EditText etUsername, etLocation;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);

        etUsername = findViewById(R.id.et_user_name);
        etLocation = findViewById(R.id.et_location);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            dbHelper = new MyDatabaseHelper(this);
            dbHelper.addUser(etUsername.getText().toString(), etLocation.getText().toString());
        }
    }
}