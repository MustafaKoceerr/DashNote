package com.mustafakoceerr.dashnote.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafakoceerr.dashnote.core.database.dao.NoteDao
import com.mustafakoceerr.dashnote.core.database.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DashNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}