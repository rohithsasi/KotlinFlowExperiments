package com.nike.userresourcesdemo.repository

import android.content.Context
import com.nike.userresourcesdemo.database.UserResourcesDatabase
import com.nike.userresourcesdemo.model.Note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepository(context: Context) {
    private val database = UserResourcesDatabase.getInstance(context)

    fun getAllNotes(): Flow<List<Note>> = database.noteDao.getAll()

    suspend fun addNote(note: Note) = withContext(IO) {
        database.noteDao.insert(note)
    }

    suspend fun deleteNote(note: Note) = withContext(IO) {
        database.noteDao.delete(note)
    }
}