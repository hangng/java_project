package com.example.n3333.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.n3333.myapplication.Adapters.MoviesAdapter;
import com.example.n3333.myapplication.module.Movies;

import java.util.ArrayList;
import java.util.List;

public class MoviesApp extends AppCompatActivity implements MoviesAdapter.Listener {

    private List<Movies> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        SnapHelper startSnapHelper = new LinearSnapHelper();
        mAdapter = new MoviesAdapter(movieList,this);
        linearLayoutManager = new LinearLayoutManager(this);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        startSnapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        for (int i = 0; i < movieList.size(); i++) {
            Movies movie = movieList.get(i);
            boolean found = false;
            if (movie.getCheckpoint().equals("1")) {
                Toast.makeText(this, "found", Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }

    private void prepareMovieData() {
        Movies movie = new Movies("Mad Max: Fury Road", "Action & Adventure", "2015", "2");
        movieList.add(movie);

        movie = new Movies("Inside Out", "Animation, Kids & Family", "2015", "2");
        movieList.add(movie);

        movie = new Movies("Star Wars: Episode VII - The Force Awakens", "Action", "2015", "1");
        movieList.add(movie);

        movie = new Movies("Shaun the Sheep", "Animation", "2015", "2");
        movieList.add(movie);

        movie = new Movies("The Martian", "Science Fiction & Fantasy", "2015", "2");
        movieList.add(movie);

        movie = new Movies("Mission: Impossible Rogue Nation", "Action", "2015", "1");
        movieList.add(movie);

        movie = new Movies("Up", "Animation", "2009", "2");
        movieList.add(movie);

        movie = new Movies("Star Trek", "Science Fiction", "2009", "1");
        movieList.add(movie);

        movie = new Movies("The LEGO Movies", "Animation", "2014", "2");
        movieList.add(movie);

        movie = new Movies("Iron Man", "Action & Adventure", "2008", "1");
        movieList.add(movie);

        movie = new Movies("Aliens", "Science Fiction", "1986", "2");
        movieList.add(movie);

        movie = new Movies("Chicken Run", "Animation", "2000", "2");
        movieList.add(movie);

        movie = new Movies("Back to the Future", "Science Fiction", "1985", "1");
        movieList.add(movie);

        movie = new Movies("Raiders of the Lost Ark", "Action & Adventure", "1981", "2");
        movieList.add(movie);

        movie = new Movies("Goldfinger", "Action & Adventure", "1965", "1");
        movieList.add(movie);

        movie = new Movies("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014", "2");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int iPosition) {

    }
}