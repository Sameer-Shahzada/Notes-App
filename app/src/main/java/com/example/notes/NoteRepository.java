package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;    // mWordDao
    private LiveData<List<Note>> mAllNotes; // mAllWords;

    // Note that in order to unit test the NoteRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
        mAllNotes = noteDao.getAllNotes();  //getAlphabetizedWords
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Note>> getAllNotesM() {
        return mAllNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });

//        void delete(Note note) {
//            NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
//                noteDao.delete(note);
//            });
//
//        }
    }
}