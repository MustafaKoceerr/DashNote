package com.mustafakoceerr.dashnote.core.database.di

import android.content.Context
import androidx.room.Room
import com.mustafakoceerr.dashnote.core.database.DashNoteDatabase
import com.mustafakoceerr.dashnote.core.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDashNoteDatabase(
        @ApplicationContext context: Context
    ): DashNoteDatabase {
        return Room.databaseBuilder(
            context,
            DashNoteDatabase::class.java,
            "dashnote_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: DashNoteDatabase): NoteDao {
        return database.noteDao()
    }
}