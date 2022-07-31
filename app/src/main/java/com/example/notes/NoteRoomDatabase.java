package com.example.notes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)

    public abstract class NoteRoomDatabase extends RoomDatabase {

        public abstract NoteDao noteDao();

    // marking the instance as volatile to ensure atomic access to the variable
        private static volatile NoteRoomDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static NoteRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (NoteRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                NoteRoomDatabase.class, "note_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NoteDao dao = INSTANCE.noteDao();
                dao.deleteAll();

                Note note = new Note("Hello");
                dao.insert(note);
                note = new Note("World");
                dao.insert(note);
            });
        }
    };
    }

