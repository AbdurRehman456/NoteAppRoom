package com.hydratech.notesapproomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes ::class] , version = 1)
abstract class DatabaseNotes : RoomDatabase() {
  abstract fun getNotesDao() : DaoNotes

  companion object{
    @Volatile
    private var INSTANCE : DatabaseNotes? =null
    fun getDatabaseNotes(context:Context) : DatabaseNotes{
      if (INSTANCE == null){
        synchronized(this){
          INSTANCE = Room.databaseBuilder(context.applicationContext,DatabaseNotes::class.java,"notesDb").build()
        }
      }
      return INSTANCE!!
    }

  }


}