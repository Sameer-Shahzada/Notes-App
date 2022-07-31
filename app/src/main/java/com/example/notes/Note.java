package com.example.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// define room entity with new table name by tableName property
@Entity(tableName = "notes_table")

public class Note {

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "note")
    private String mNote;


    public Note(@NonNull String note) {
        this.mNote = note;
    }
    @NonNull
    public String getNote() {
        return this.mNote;
    }

}
