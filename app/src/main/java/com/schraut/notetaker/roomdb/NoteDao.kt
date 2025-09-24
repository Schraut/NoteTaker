package com.schraut.notetaker.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    // This will create a new Note
    @Insert
    suspend fun insert(note: Note)

    // This query will get all entries/records from the notes table
    @Query("Select * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>
}