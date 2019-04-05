package com.example.myapplication;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase INSTANCE;

    public static NoteDatabase getINSTANCE(Context ctx) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            NoteDatabase.class, "noteDatabase").fallbackToDestructiveMigration()
                            .addCallback(noteCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback noteCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateNoteDb(INSTANCE.getNoteDao()).execute();
        }
    };

    private static class populateNoteDb extends AsyncTask<Void, Void, Void> {

        NoteDao noteDao;

        populateNoteDb(NoteDao dao) {
            noteDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Note 1", "Description 1", 1));
            noteDao.insert(new Note("Note 2", "Description 2", 2));
            noteDao.insert(new Note("Note 3", "Description 3", 1));
            noteDao.insert(new Note("Note 4", "Description 4", 6));
            return null;
        }
    }

    public abstract NoteDao getNoteDao();
}
