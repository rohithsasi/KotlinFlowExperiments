package com.nike.userresourcesdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.nike.userresourcesdemo.extension.observe
import com.nike.userresourcesdemo.model.Note
import com.nike.userresourcesdemo.viewmodel.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var resourcesViewModel: ResourcesViewModel
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var locationViewModel: LocationViewModel

    private var noteToDelete: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this).run {
            userViewModel = get()
            resourcesViewModel = get()
            noteViewModel = get()
            locationViewModel = get()
        }

        observeUsers()
        observeResources()
        observeNotes()
        observeLocation()

        geAllUsersButton.setOnClickListener { userViewModel.getAllUsers() }
        getUsersByZipcodeButton.setOnClickListener { userViewModel.getUsersByZipcode("92998-3874") }
        getResourcesByUserIdButton.setOnClickListener { resourcesViewModel.getResourcesByUserId(1) }
        getAllNotesButton.setOnClickListener { noteViewModel.getAllNotes() }
        addNoteButton.setOnClickListener { noteViewModel.addNote(Note("Created on ${Date()}")) }
        deleteNoteButton.setOnClickListener {
            noteToDelete?.let {
                noteViewModel.deleteNote(it)
            }
        }

        getLocationButton.setOnClickListener { locationViewModel.requestLocationUpdates() }
    }

    private fun observeLocation() {
        locationViewModel.currentLocation.observe(this, {
            Log.d(TAG, "Retrieved current location: $it")
        }, {
            Log.e(TAG, "Failed to retrieve current location", it)
        })
    }

    private fun observeNotes() {
        noteViewModel.notes.observe(this, {
            it.forEachIndexed { index, note -> Log.d(TAG, "Retrieved note[$index] = $note") }
            noteToDelete = it.lastOrNull()
        }, {
            Log.e(TAG, "Failed to retrieve notes", it)
        })
    }

    private fun observeResources() {
        resourcesViewModel.resources.observe(this, {
            it.posts.forEachIndexed { index, post -> Log.d(TAG, "Retrieved post[$index] = $post") }
            it.albums.forEachIndexed { index, album -> Log.d(TAG, "Retrieved album[$index] = $album") }
            it.todos.forEachIndexed { index, todo -> Log.d(TAG, "Retrieved todo[$index] = $todo") }
        }, {
            Log.e(TAG, "Failed to retrieve resources", it)
        })
    }

    private fun observeUsers() {
        userViewModel.users.observe(this, {
            it.forEachIndexed { index, user -> Log.d(TAG, "Retrieved user[$index] = $user") }
        }, {
            Log.e(TAG, "Failed to retrieve users", it)
        })
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}
