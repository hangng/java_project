package com.example.n3333.genting_tech_tests;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import fragments.CameraFr;
import fragments.GalleryFr;

public class FragmentActivity extends AppCompatActivity {
    private TabLayout mTlTab;
    private TextView  mTvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_fragment_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        mTvTitle = toolbar.findViewById(R.id.tv_title);
        initCom();
    }

    private void initCom() {
        mTlTab = (TabLayout) findViewById(R.id.tab);

        replaceFragment(R.string.tab_one, CameraFr.newInstance());

        // Create a new Tab named "First"
        TabLayout.Tab firstTab = mTlTab.newTab();
        firstTab.setText(getString(R.string.tab_one)); // set the Text for the first Tab
        mTlTab.addTab(firstTab); // add  the tab at in the TabLayout
        // perfo


        // Create a new Tab named "First"
        TabLayout.Tab secTab = mTlTab.newTab();
        secTab.setText(getString(R.string.tab_two)); // set the Text for the first Tab
        mTlTab.addTab(secTab); // add  the tab at in the TabLayout
        // perfo

        mTlTab.setOnTabSelectedListener(mOnTabSelectedListener);
    }


    public void replaceFragment(int tag, Fragment fragment) {
        FragmentTransaction frTransaction = getSupportFragmentManager().beginTransaction();
        //frTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_bottom, R.animator.slide_in_left, R.animator.slide_out_bottom);
//        frTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        frTransaction.addToBackStack(getString(tag));
        frTransaction.replace(R.id.fragment_container, fragment, getString(tag));
        frTransaction.commit();
    }

    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Bundle bundle = new Bundle();
            clearStack();
            switch (tab.getPosition()) {
                case 0:

                    popFragment(R.string.tab_two);
                    replaceFragment(R.string.tab_one, CameraFr.newInstance());
//                    mTvTitle.setText(R.string.tab_one);
                    break;

                case 1:
                    popFragment(R.string.tab_one);
                    replaceFragment(R.string.tab_two, GalleryFr.newInstance());
//                    mTvTitle.setText(R.string.tab_two);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public void clearStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void popFragment(int tagId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(getString(tagId), 1);
    }

    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fr;

        fr = getActiveFragment();

        if (fr != null) {
            Log.i("", "Fr is not null");
        } else {
            Log.i("", "Fr is  null");
            return;
        }

        if (fm.getBackStackEntryCount() != 1 || fm.getBackStackEntryCount() == 1) {
            finish();
        }

        super.onBackPressed();
    }

    public Fragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }


}
