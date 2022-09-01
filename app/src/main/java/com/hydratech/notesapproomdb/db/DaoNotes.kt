package com.hydratech.notesapproomdb.db

import androidx.room.*

@Dao
interface DaoNotes {

    @Insert
     suspend fun insert(notes: Notes)

    @Query("SELECT * FROM Notes ORDER BY  id DESC")
    suspend fun getNotes() : List<Notes>

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)
}