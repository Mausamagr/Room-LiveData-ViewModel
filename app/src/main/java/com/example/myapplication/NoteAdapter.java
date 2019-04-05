package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
*
ListAdapter helps you to work with RecyclerViews that change the content over time.
All you need to do is submit a new list. It runs the DiffUtil tool behind the scenes for you on the background thread.
Then it runs the animations based on how the list has changed. This is all handled via very simple API.
 */

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.noteViewHolder> {

    Context mContext;
    LayoutInflater inflater;

    public NoteAdapter(Context ctx) {
        super(item_callback);
        mContext = ctx;
        inflater = LayoutInflater.from(mContext);
    }

    private static final DiffUtil.ItemCallback<Note> item_callback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
            return (note.getTitle().equals(t1.getTitle()) && note.getDescription().equals(t1.getDescription()) && note.getPriority() == t1.getPriority());
        }
    };


    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new noteViewHolder(view);
    }

    public Note getNoteAt(int index) {
        return getItem(index);
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder noteViewHolder, int i) {
        Note note = getItem(i);
        noteViewHolder.title.setText(note.getTitle());
        noteViewHolder.Description.setText(note.getDescription());
        noteViewHolder.priority.setText(String.valueOf(note.getPriority()));
    }

    public static class noteViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView Description;
        TextView priority;

        public noteViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            Description = view.findViewById(R.id.description);
            priority = view.findViewById(R.id.priority);
        }
    }
}
