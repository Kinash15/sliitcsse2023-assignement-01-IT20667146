package com.example.noteapplication.Database

import androidx.lifecycle.LiveData
import com.example.noteapplication.Models.Note

class NotesRepository(private val noteDuo: NoteDuo) {

    val allNotes : LiveData<List<Note>> = noteDuo.getAllNotes()

    suspend fun insert(note: Note){

        noteDuo.insert(note)

    }

    suspend fun delete(note: Note) {

        noteDuo.delete(note)
    }


    suspend fun update(note: Note) {

        noteDuo.update(note.id, note.title, note.note)


    }

        }