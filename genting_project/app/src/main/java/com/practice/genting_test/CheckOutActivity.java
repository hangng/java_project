package com.practice.genting_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.practice.genting_test.adapter.CheckOutAdapter;
import com.practice.genting_test.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {
    private RecyclerView mRvCheckOut;
    private LinearLayoutManager mLMgr;
    private CheckOutAdapter mAdpCheckOut;
    private ArrayList<User> mAryUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_activity);

        mRvCheckOut = findViewById(R.id.rv_check_out);
        mLMgr = new LinearLayoutManager(this);
        mAdpCheckOut = new CheckOutAdapter(mAryUser);
        mRvCheckOut.setLayoutManager(mLMgr);
        mRvCheckOut.setAdapter(mAdpCheckOut);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}