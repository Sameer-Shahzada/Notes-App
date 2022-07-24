package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// define room entity with new table name by tableName property
@Entity(tableName = "notes_table")

public class Note {

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "text")
    public String text;


}
