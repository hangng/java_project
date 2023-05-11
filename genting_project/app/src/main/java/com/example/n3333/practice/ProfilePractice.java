package com.example.n3333.practice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ProfilePractice extends AppCompatActivity {

//    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    TextView mTxtv;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_practice);
        mTxtv = findViewById(R.id.txtview);

//        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
//        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Do work to refresh the list here.
//                new Task().execute();
//            }
//        });
    }


    private class Task extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
//            mWaveSwipeRefreshLayout.setRefreshing(false);
            mTxtv.setText(counter+"");
            super.onPostExecute(result);
        }
    }
}
