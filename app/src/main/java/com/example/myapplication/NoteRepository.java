package com.example.myapplication;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    NoteDao noteDao;

    LiveData<List<Note>> allNotes;


    public NoteRepository(Context ctx) {
        NoteDatabase database = NoteDatabase.getINSTANCE(ctx.getApplicationContext());
        noteDao = database.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        new executeQueryAsyncTask(noteDao, 0).execute(note);
    }

    public void update(Note note) {
        new executeQueryAsyncTask(noteDao, 1).execute(note);
    }

    public void delete(Note note) {
        new executeQueryAsyncTask(noteDao, 2).execute(note);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(noteDao).execute();
    }

    private static class executeQueryAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;
        int whatToDo;

        executeQueryAsyncTask(NoteDao dao, int value) {
            noteDao = dao;
            whatToDo = value;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            switch (whatToDo) {
                case 0:
                    noteDao.insert(notes[0]);
                    break;
                case 1:
                    noteDao.update(notes[0]);
                    break;
                case 2:
                    noteDao.delete(notes[0]);
                    break;
                case 3:
                    noteDao.deleteAll();
            }
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        NoteDao noteDao;

        deleteAllAsyncTask(NoteDao dao) {
            noteDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
