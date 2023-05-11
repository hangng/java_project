package com.example.n3333.genting_tech_tests.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.genting_tech_tests.R;
import com.example.n3333.genting_tech_tests.module.SortingRvItem;

import java.util.ArrayList;

public class SortingRvAdapter extends RecyclerView.Adapter<SortingRvAdapter.SortingViewHolder> {
    private Listenenr mCallBack;
    private ArrayList<SortingRvItem> mExampleList;
    private String msChangeText, msReceiveText;

    public SortingRvAdapter(ArrayList<SortingRvItem> exampleList, Listenenr callback, String sReceiveText) {
        this.mCallBack = callback;
        this.mExampleList = exampleList;
        this.msReceiveText = sReceiveText;
    }

    @Override
    public SortingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sortingitem, parent, false);
        SortingViewHolder evh = new SortingViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final SortingViewHolder holder, final int position) {
        SortingRvItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(String.valueOf(currentItem.getInteger()));
        holder.mEtText.setText(currentItem.getText1());
        holder.mEtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                msChangeText = editable.toString();
                mCallBack.onChangeText(msChangeText, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<SortingRvItem> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }

    public interface Listenenr {
        void onChangeText(String msText, int iPosition);
    }

    public static class SortingViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public EditText mEtText;


        public SortingViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.tv_show_text);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mEtText = itemView.findViewById(R.id.et_changing);
        }
    }
}
