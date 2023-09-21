package com.example.noteapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapplication.Models.Note


@Dao
interface NoteDuo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from note_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("UPDATE note_table Set title = :title, note = :note WHERE id = :id")
    suspend fun update(id : Int?, title : String?, note : String?)



}