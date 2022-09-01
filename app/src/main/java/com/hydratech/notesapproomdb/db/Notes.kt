package com.hydratech.notesapproomdb.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedAutoMigrationSpec

@Entity
data class Notes(
    @ColumnInfo(name = "title") var title :String,
    @ColumnInfo(name = "note") var note :String
    ){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}