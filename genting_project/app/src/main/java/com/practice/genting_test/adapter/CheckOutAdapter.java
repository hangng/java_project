package com.practice.genting_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.practice.genting_test.model.User;

import java.util.ArrayList;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {

    private ArrayList<User> mUserLst = new ArrayList<>();
    public Context mContext;

    public CheckOutAdapter(ArrayList<User> aryUser) {
        this.mUserLst = aryUser;
    }

    @NonNull
    @Override
    public CheckOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_check_out,parent,false);
        return new VHCheckOut(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapter.ViewHolder holder, int position) {
        User user = mUserLst.get(position);


    }

    @Override
    public int getItemCount() {
        return mUserLst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class VHCheckOut extends ViewHolder {

        public VHCheckOut(View itemView) {
            super(itemView);
        }
    }
}
