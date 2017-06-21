package com.pienkowska.swim.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SongFragment extends Fragment {
    private View root;
    private Song song;

    private MediaPlayer player;
    private static final int SKIP_JUMP = 5000; // 5 sec

    private SeekBar slider;
    private ImageButton play;

    public SongFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_song, container, false);
        song = SongManager.getInstance().current();

        initTextViews();
        initPlayer();
        initButtons();

        return root;
    }

    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    private void initTextViews() {
        TextView author = (TextView) root.findViewById(R.id.authorTV);
        TextView title = (TextView) root.findViewById(R.id.titleTV);
        author.setText(song.getAuthor());
        title.setText(song.getTitle());
    }

    private void initPlayer() {
        player = MediaPlayer.create(getActivity(), song.getId());
        initSlider();
        player.start();
    }

    private void initSlider() {
        slider = (SeekBar) root.findViewById(R.id.sliderSB);
        final Handler handler = new Handler();
        slider.setMax(player.getDuration() / 1000);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(player != null){
                    int pos = player.getCurrentPosition() / 1000;
                    slider.setProgress(pos);
                    if(slider.getProgress() >= slider.getMax() - 1) {
                        player.seekTo(0);
                        slider.setProgress(0);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(player != null && fromUser){
                    player.seekTo(progress * 1000);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void initButtons() {
        play = (ImageButton) root.findViewById(R.id.playIB);
        ImageButton forward = (ImageButton) root.findViewById(R.id.forwardIB);
        ImageButton rewind = (ImageButton) root.findViewById(R.id.rewindIB);
        ImageButton next = (ImageButton) root.findViewById(R.id.nextIB);
        ImageButton prev = (ImageButton) root.findViewById(R.id.prevIB);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) {
                    player.pause();
                    play.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                }
                else {
                    player.start();
                    play.setImageResource(R.drawable.ic_pause_white_48dp);
                    Toast.makeText(getActivity(), (player.isPlaying() ? "YES" : "NO"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() + SKIP_JUMP);
                int progress = slider.getProgress() + SKIP_JUMP / 1000;
                if(progress > slider.getMax())
                    progress = slider.getMax();
                slider.setProgress(progress);
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() - SKIP_JUMP);
                int progress = slider.getProgress() - SKIP_JUMP / 1000;
                if(progress < 0)
                    progress = 0;
                slider.setProgress(progress);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                SongManager.getInstance().next();
                reload();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                SongManager.getInstance().prev();
                reload();
            }
        });
    }

    private void reload() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
        fm.beginTransaction().replace(R.id.containerFL, new SongFragment())
                .addToBackStack(null).commit();
    }
}
