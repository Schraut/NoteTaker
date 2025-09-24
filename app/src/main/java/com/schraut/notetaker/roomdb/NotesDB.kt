package com.schraut.notetaker.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB: RoomDatabase() {
    abstract val notesDB: NoteDao
    companion object {
        // The @Volatile annotation ensures that a variable's value is always
        // read from and written to main memory, bypassing the CPU's cache. This prevents one thread
        // from using stale, cached data by making sure all threads see the most up-to-date value of
        // the variable, thereby ensuring memory visibility and preventing potential concurrency
        // issues in multithreaded environments
        @Volatile
        private var INSTANCE : NotesDB? = null

        fun getInstance(context: Context): NotesDB {
            // When a method or a block of code is marked as synchronized, only one thread can
            // execute that section of code at any given time. Other threads attempting to enter the
            // synchronized section will be blocked and wait until the current thread exits.
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    // Database object
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDB::class.java,
                        name = "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }

}