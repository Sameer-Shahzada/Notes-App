package com.example.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */


public class NoteViewModel extends AndroidViewModel {

        private NoteRepository mRepository;

        private final LiveData<List<Note>> mAllNotes;

        public NoteViewModel (Application application) {
            super(application);
            mRepository = new NoteRepository(application);
            mAllNotes = mRepository.getAllNotesM();
        }

        LiveData<List<Note>> getAllNotesM() { return mAllNotes; }

        public void insert(Note note)
        {
            mRepository.insert(note);
        }
}

