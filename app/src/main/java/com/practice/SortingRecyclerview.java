package com.example.n3333.genting_tech_tests;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.genting_tech_tests.Adapters.SortingRvAdapter;
import com.example.n3333.genting_tech_tests.module.SortingRvItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortingRecyclerview extends AppCompatActivity implements SortingRvAdapter.Listenenr {

    private ArrayList<SortingRvItem> mExampleList;
    private ArrayAdapter<String> mListViewAdapter;
    private SortingRvAdapter mRecyclerViewAdapter;
    private String[] mArrayNames = new String[]{"Daryl", "Rick", "Abraham", "Eugene"};
    private EditText editText, editText2;
    private String msMainChangeText;
    private SortingRvItem mSorting;
    private int iTotalArySize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_recyclerview);
        createExampleList();
        buildRecyclerView();
        buildListView();
        editText = findViewById(R.id.edittext);
        editText2 = findViewById(R.id.edittex2);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        iTotalArySize = mExampleList.size();

        if (mSorting == null) {
            mSorting = new SortingRvItem();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                filter(s.toString());
                msMainChangeText = s.toString();

                mSorting.setText1(msMainChangeText);
                if (!msMainChangeText.isEmpty()) {
                    double devide = Double.parseDouble(msMainChangeText);
                    editText2.setText("devided  = " + (devide / iTotalArySize));
                    mSorting.setText1(msMainChangeText);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });


        Button buttonSort = findViewById(R.id.button_sort);
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArray();
                sortArrayList();
            }
        });
    }

    private void filter(String text) {
        ArrayList<SortingRvItem> filteredList = new ArrayList<>();

        for (SortingRvItem item : mExampleList) {
            Log.d("TAG", "checking text");
//            if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
            if (item.getText1().toLowerCase().startsWith(text.toLowerCase())) {
                filteredList.add(item);
            }

        }

        mRecyclerViewAdapter.filterList(filteredList);
    }

    private void sortArray() {
        Arrays.sort(mArrayNames);
        mListViewAdapter.notifyDataSetChanged();
    }

    private void sortArrayList() {
        Collections.sort(mExampleList, new Comparator<SortingRvItem>() {
            @Override
            public int compare(SortingRvItem o1, SortingRvItem o2) {
                return o1.getInteger().compareTo(o2.getInteger());
            }
        });

        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new SortingRvItem(R.drawable.happy, "Active", "1", 1));
        mExampleList.add(new SortingRvItem(R.drawable.sad, "Inactive", "12", 2));
        mExampleList.add(new SortingRvItem(R.drawable.mad, "Active", "2", 12));
        mExampleList.add(new SortingRvItem(R.drawable.mad, "Inactive", "2", 12));
    }

    private void buildRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewAdapter = new SortingRvAdapter(mExampleList, this, msMainChangeText);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void buildListView() {
        ListView listView = findViewById(R.id.listView);
        mListViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrayNames);
        listView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onChangeText(String sText, int iPosition) {

        Toast.makeText(this, "" + sText + " " + iPosition, Toast.LENGTH_SHORT).show();
    }
}
