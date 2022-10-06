package com.example.n3333.myapplication;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    private TextView mTvTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        mTvTitle = view.findViewById(R.id.lblListHeader);
        move(mTvTitle);
        return view;
    }

    public static void move(final TextView view){
        ValueAnimator va = ValueAnimator.ofFloat(0f, 100f);
        int mDuration = 3000; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        va.setRepeatCount(5);
        va.start();
    }
}
