package com.example.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy

    @Insert(onConflict = OnConflictStrategy.IGNORE)     // to ignore same strategy
    void insert(Note note);     // to insert note

    @Delete
    void delete(Note note);     // to delete note

    @Query("Select * from notes_table order by id ASC")
    List<Note> getAllNotes();       // to get all notes.


}
