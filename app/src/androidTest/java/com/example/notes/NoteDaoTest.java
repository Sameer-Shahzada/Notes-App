package com.example.notes;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4.class)
public class NoteDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private NoteDao noteDao;
    private NoteRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, NoteRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        noteDao = mDb.noteDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        Note note = new Note("note");
        noteDao.insert(note);
        List<Note> allWords = LiveDataTestUtil.getValue(noteDao.getAllNotes());
        assertEquals(allWords.get(0).getNote(), note.getNote());
    }

    @Test
    public void getAllWords() throws Exception {
        Note note = new Note("aaa");
        noteDao.insert(note);
        Note note2 = new Note("bbb");
        noteDao.insert(note2);
        List<Note> allWords = LiveDataTestUtil.getValue(noteDao.getAllNotes());
        assertEquals(allWords.get(0).getNote(), note.getNote());
        assertEquals(allWords.get(1).getNote(), note2.getNote());
    }

    @Test
    public void deleteAll() throws Exception {
        Note note = new Note("note");
        noteDao.insert(note);
        Note note2 = new Note("note2");
        noteDao.insert(note2);
        noteDao.deleteAll();
        List<Note> allWords = LiveDataTestUtil.getValue(noteDao.getAllNotes());
        assertTrue(allWords.isEmpty());
    }
}
