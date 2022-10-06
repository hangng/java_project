package com.example.n3333.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.myapplication.DataHelper;
import com.example.n3333.myapplication.R;

public class ListAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_rvfragment, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return DataHelper.title.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemText;
        private ImageView mItemImage;

        public ListViewHolder(View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mItemText = itemView.findViewById(R.id.itemText);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mItemText.setText(DataHelper.title[position]);
            mItemImage.setImageResource(
                    DataHelper.picturePath[position]);
        }

        public void onClick(View view) {
        }
    }
}
