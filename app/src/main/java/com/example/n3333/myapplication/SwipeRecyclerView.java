package com.example.n3333.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.myapplication.SwipeRvTutorial.Player;
import com.example.n3333.myapplication.SwipeRvTutorial.PlayersDataAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SwipeRecyclerView extends AppCompatActivity {

    private PlayersDataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view);

        setPlayersDataAdapter();
        setupRecyclerView();
    }

    private void setPlayersDataAdapter() {
        List<Player> players = new ArrayList<>();
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("players.csv"));

            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            String[] st;
            while ((line = reader.readLine()) != null) {
                st = line.split(",");
                Player player = new Player();
                player.setName(st[0]);
                player.setNationality(st[1]);
                player.setClub(st[4]);
                player.setRating(Integer.parseInt(st[9]));
                player.setAge(Integer.parseInt(st[14]));
                players.add(player);
            }
        } catch (IOException e) {

        }

        mAdapter = new PlayersDataAdapter(players);
    }


    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
