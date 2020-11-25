package com.nike.userresourcesdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.extension.getExceptionHandler
import com.nike.userresourcesdemo.extension.launchAndCollect
import com.nike.userresourcesdemo.model.Note
import com.nike.userresourcesdemo.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository(application)
    private val _notes = MutableLiveData<Result<List<Note>>>()

    val notes: LiveData<Result<List<Note>>>
        get() = _notes

    fun getAllNotes() = viewModelScope.launchAndCollect(_notes) {
        repository.getAllNotes()
    }

    fun addNote(note: Note) = viewModelScope.launch(_notes.getExceptionHandler()) {
        repository.addNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(_notes.getExceptionHandler()) {
        repository.deleteNote(note)
    }
}
