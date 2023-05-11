package com.example.n3333.practice.AndroidMe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.n3333.practice.R;

public class BodyPartFragment extends Fragment {

    public BodyPartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        ImageView bodyParts = (ImageView) rootView.findViewById(R.id.img_body_part);

        bodyParts.setImageResource(AndroidImageAssets.getHeads().get(0));

        return rootView;
    }
}
