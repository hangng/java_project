package com.example.n3333.practice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_recycler_view);

        ListFragment fragment = new ListFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder,fragment);
        fragmentTransaction.commit();
    }
}
