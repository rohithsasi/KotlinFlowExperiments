package com.nike.userresourcesdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nike.userresourcesdemo.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
internal abstract class UserResourcesDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        private var instance: UserResourcesDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserResourcesDatabase = instance ?: Room.databaseBuilder(
            context.applicationContext,
            UserResourcesDatabase::class.java,
            "user-resources.db"
        ).build().also { instance = it }
    }
}