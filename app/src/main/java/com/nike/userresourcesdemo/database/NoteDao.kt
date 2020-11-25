package com.nike.userresourcesdemo.database

import androidx.room.*
import com.nike.userresourcesdemo.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
internal interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAll(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}