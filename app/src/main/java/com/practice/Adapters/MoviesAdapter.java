package com.example.n3333.genting_tech_tests.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.genting_tech_tests.R;
import com.example.n3333.genting_tech_tests.module.Movies;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private List<Movies> moviesList;
    private Listener mCallback;


    public MoviesAdapter(List<Movies> moviesList, Listener callback) {
        this.moviesList = moviesList;
        this.mCallback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movies movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.mRlRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        holder.ivRv.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface Listener{

        void onClick(int iPosition);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        private RelativeLayout mRlRow;
        private ImageView ivRv;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            genre = view.findViewById(R.id.genre);
            year = view.findViewById(R.id.year);
            ivRv = view.findViewById(R.id.iv_rv);
            mRlRow = view.findViewById(R.id.rl_row);
        }
    }

}
