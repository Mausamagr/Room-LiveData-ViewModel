package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.noteViewHolder> {

    Context mContext;
    List<Note> notes;
    LayoutInflater inflater;

    public NoteAdapter(Context ctx) {
        mContext = ctx;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Note> data) {
        this.notes = data;
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new noteViewHolder(view);
    }

    public Note getNoteAt(int index) {
        return notes.get(index);
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder noteViewHolder, int i) {
        Note note = notes.get(i);
        noteViewHolder.title.setText(note.getTitle());
        noteViewHolder.Description.setText(note.getDescription());
        noteViewHolder.priority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes != null? notes.size() : 0;
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
