package com.example.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

        private NoteRepository mRepository;

        private final LiveData<List<Note>> mAllNotes;

        public NoteViewModel (Application application) {
            super(application);
            mRepository = new NoteRepository(application);
            mAllNotes = mRepository.getAllWords();
        }

        LiveData<List<Note>> getAllWords() { return mAllNotes; }

        public void insert(Note note) { mRepository.insert(note); }
}

