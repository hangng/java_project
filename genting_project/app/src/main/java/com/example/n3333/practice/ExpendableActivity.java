package com.example.n3333.practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpendableActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expendable_listview);

        // get the listview
        expListView = findViewById(R.id.lvExp);

        // preparing list data
      prepareListData();

//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild) {
//            @Override
//            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
//
//            }
//
//            @Override
//            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
//
//            }
//
//            @Override
//            public int getGroupCount() {
//                return 0;
//            }
//
//            @Override
//            public int getChildrenCount(int i) {
//                return 0;
//            }
//
//            @Override
//            public Object getGroup(int i) {
//                return null;
//            }
//
//            @Override
//            public Object getChild(int i, int i1) {
//                return null;
//            }
//
//            @Override
//            public long getGroupId(int i) {
//                return 0;
//            }
//
//            @Override
//            public long getChildId(int i, int i1) {
//                return 0;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//                return false;
//            }
//
//            @Override
//            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//                return null;
//            }
//
//            @Override
//            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//                return null;
//            }
//
//            @Override
//            public boolean isChildSelectable(int i, int i1) {
//                return false;
//            }
//
//            @Override
//            public boolean areAllItemsEnabled() {
//                return false;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public void onGroupExpanded(int i) {
//
//            }
//
//            @Override
//            public void onGroupCollapsed(int i) {
//
//            }
//
//            @Override
//            public long getCombinedChildId(long l, long l1) {
//                return 0;
//            }
//
//            @Override
//            public long getCombinedGroupId(long l) {
//                return 0;
//            }
//        };
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        Log.d("EL","PreparedLisdata called...");
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

       // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
