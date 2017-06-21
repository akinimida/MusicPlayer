package com.pienkowska.swim.musicplayer;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSongs();

        if (findViewById(R.id.containerFL) != null) {
            if (savedInstanceState != null)
                return;

            ListFragment fragment = new ListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerFL, fragment).commit();
        }
    }

    private void initSongs() {
        SongManager sm = SongManager.getInstance();
        Song s = new Song(R.raw.jk_fov1, "Jespyr Kyd", "Flight Over Venice 1");
        sm.addSong(s);
        s = new Song(R.raw.sw_cantina, "Star Wars", "Cantina Theme");
        sm.addSong(s);
        s = new Song(R.raw.sw_jabbaflow, "Star Wars", "Jabba Flow");
        sm.addSong(s);
    }
}
