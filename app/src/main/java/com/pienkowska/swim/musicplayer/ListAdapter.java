package com.pienkowska.swim.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Song> songs;

    public ListAdapter(Context context) {
        this.context = context;
        songs = SongManager.getInstance().songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_list_row_white, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Song song = songs.get(position);
        holder.author.setText(song.getAuthor());
        holder.title.setText(song.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongManager.getInstance().setCurrent(position);
                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerFL, new SongFragment())
                        .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.authorTV);
            title = (TextView) itemView.findViewById(R.id.titleTV);
        }
    }
}
