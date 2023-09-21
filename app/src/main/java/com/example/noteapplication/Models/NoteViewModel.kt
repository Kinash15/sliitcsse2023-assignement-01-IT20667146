package com.example.noteapplication.Models

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapplication.Database.NoteDatabase
import com.example.noteapplication.Database.NotesRepository
import kotlinx.coroutines.Dispatchers

class NoteViewModel(application: Application) :ViewModelProvider.AndroidViewModel(application),
    Parcelable {

    private val repository : NotesRepository

    val allnotes : LiveData<List<Note>>


    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDuo()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.update(note)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteViewModel> {
        override fun createFromParcel(parcel: Parcel): NoteViewModel {
            return NoteViewModel(parcel)
        }

        override fun newArray(size: Int): Array<NoteViewModel?> {
            return arrayOfNulls(size)
        }
    }


}