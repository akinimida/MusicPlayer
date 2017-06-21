package com.pienkowska.swim.musicplayer;

import java.util.ArrayList;

class SongManager {
    ArrayList<Song> songs;
    private static SongManager instance = null;
    private int current = -1;

    private SongManager() {
        songs = new ArrayList<>();
    }

    static SongManager getInstance() {
        if(instance == null)
            instance = new SongManager();
        return instance;
    }

    void addSong(Song song) {
        songs.add(song);
    }

    void setCurrent(int index) {
        current = index;
    }

    Song current() {
        return songs.get(current);
    }

    void next() {
        current++;
        if(current >= songs.size())
            current = 0;
    }

    void prev() {
        current--;
        if(current < 0)
            current = 0;
    }
}
